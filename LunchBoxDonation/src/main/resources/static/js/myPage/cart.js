/**
 * @author: chaek 2021-09-15 ~
 */
$(document).ready(function () {
    // console.log('pc cart.js');
    addBtnEvent();
    // getUserCouponList();
    loadRecommendProduct();
});

function addBtnEvent() {
    $('.btn-qty.cart').on('click', updateProductQty);
    $('.deleteUserCart').on('click', deleteUserCart);
    $('#checkAll').on('click', checkAll);
    $('.checkboxGroup').on('click', checkDeliveryGroup);
    $('#deleteUserCartList').on('click', deleteUserCartList);
    $('.checkbox').on('click', checkboxFunc);
    $(document).on('click', '.useCoupon', getProductCoupon);
    $('#useCoupon').on('click', couponApply);
    $('#couponApplyCancel').on('click', couponApplyCancel);
    $('.close').on('click', closePop);
    $(document).on('click', '.radio.type-box', calcDiscount);
    $('#order').on('click', order);
    $('.btnRestock').on('click', applyRestock);
    $('.restockBtnClose').on('click', closeRestockPop);
}

function updateProductQty(e) {
    const vCartid = this.dataset.vCartid;
    const calculate = this.dataset.calculate;
    const vUproductcd = this.dataset.vUproductcdCart;
    const vGproductcd = this.dataset.vGproductcd;
    const vProductcd = this.dataset.vProductcd;
    const outOfStockStatus = this.dataset.outOfStockStatus;
    const vOptionid = this.dataset.vOptionid;
    // console.log(this.dataset);
    if (outOfStockStatus == 'Y') {
        return;
    }

    if (!$(this).parents('div.prd-info-area').parent('li').find('input[type=checkbox]').is(':checked')) {
        alert('상품 선택 후 수량을 변경할 수 있습니다.');
        return;
    }

    const discountPrice = fnOnlyNumber($('#discountPrice-' + vGproductcd).text()).number;
    // console.log('discountPrice =', discountPrice);

    if (0 < discountPrice) {
        const msg = '수량 변경시 적용된 쿠폰이 초기화 됩니다.\n수량을 변경하시겠습니까?';
        if (!confirm(msg)) {
            return;
        }
    }

    const vDlvGroupCd = this.dataset.vDlvGroupCd;
    const oriQty = parseInt($("#qty-" + vCartid).val());
    let nQty = oriQty;
    const nMaxQty = $('#qty-' + vCartid).data("max-qty");

    if (calculate == 'ADD') {
        nQty = oriQty + 1;
        $("#qty-" + vCartid).val(nQty);
        if (nMaxQty > 0 && nQty > nMaxQty) {
            $("#qty-" + vCartid).val(oriQty);
        }
    } else if (calculate == 'SUBTRACT') {
        if (nQty > 1) {
            nQty = oriQty - 1;
            if (nMaxQty > 0 && nQty > nMaxQty) {
                nQty = nMaxQty;
            }
            $("#qty-" + vCartid).val(nQty);
        } else {
            return;
        }
    } else {
        return;
    }

    const data = {
        vCartid: encodeURIComponent(vCartid),
        calculate: encodeURIComponent(calculate),
        vProductcd: encodeURIComponent(vProductcd),
        vUproductcd: encodeURIComponent(vUproductcd),
        vGproductcd: encodeURIComponent(vGproductcd),
        vOptionid: encodeURIComponent(vOptionid),
        nQty: encodeURIComponent(nQty),
        vCartType: encodeURIComponent('01')
    }

    cmAjax({
        url: '/order/cart/updateProductQtyAjax'
        , type: "post"
        , data: {
            data: JSON.stringify(data)
        }
        , dataType: "json"
        , success: function (data) {
            // console.log(data);
            if (data.status == 'succ') {
                calculateCartPricePoint(data.object);
                calculateDeliveryProductPrice(data.object);

                let nDlvPrice = 0;
                let dlvGrpCd = '';
                let dlvChargeCd = '';
                $.each(data.object.dlvMap, function (dlvGroupCd, dlvGroupInfo) {
                    if (vDlvGroupCd == dlvGroupCd) {
                        nDlvPrice = dlvGroupInfo.nDlvPrice;
                        dlvGrpCd = dlvGroupInfo.vDlvGroupCd;
                        dlvChargeCd = dlvGroupInfo.vDlvChargeCd;

                        if (dlvChargeCd == '20'){
                            nDlvPrice = dlvGroupInfo.groupAllDlvPrice;
                        }

                        return;
                    }
                });

                $('#span-dlv-price-' + dlvGrpCd + dlvChargeCd).text(SetNumComma(nDlvPrice));
                $('#delivery-group-' + vDlvGroupCd + ' input[name=defaultDeliveryPrice]').val(nDlvPrice);
                checkFreeDelivery(vDlvGroupCd);
                calculateTotalOrderPrice();

                $('#discountPrice-' + vGproductcd).text(0);

                // console.log('사용가능쿠폰 ' + data.object.usableCouponCount + '개');
                if (0 < data.object.usableCouponCount) {
                    const product = data.object.product[0];
                    const html = [];
                    html.push('<a href="javascript:void(0);" class="btn-list-option useCoupon"');
                    html.push('     style="margin-left: 5px;display: inline-block;"');
                    html.push('     data-v-productcd="' + product.VProductcd + '"');
                    html.push('     data-v-uproductcd="' + product.VUproductcdCart + '"');
                    html.push('     data-v-gproductcd="' + product.VGproductcd + '"');
                    html.push('     data-v-add-prod-yn="Y">');
                    html.push('     <span class="text-primary-ex">쿠폰적용</span>');
                    html.push('</a>');
                    $('#discountPrice-' + vGproductcd).parents('.bottom-price-box').find('a').remove();
                    $('#discountPrice-' + vGproductcd).parent('p.price-txt').append(html.join('\n'));
                } else {
                    // console.log($('#discountPrice-' + vGproductcd).parent('.bottom-price-box').find('a').length);
                    $('#discountPrice-' + vGproductcd).parents('.bottom-price-box').find('a').hide();

                }
            } else {
                alert(data.message);
                // 다시 수량 복구
                $("#qty-" + vCartid).val(oriQty);

            }
        }
        , error: function (e) {
            console.log(e);
        }
    });

}

function calculateCartPricePoint(obj) {
    console.log(obj);
    const isOrange = obj.isOrange;
    const product = obj.product;

    $.each(product, function (i, v) {

        let price = v.NCalcCartSalePrice;
        let point = parseInt(v.NCalcCartPoint);
        /*
        if (isOrange) {
            price = v.NCalcOrangeCartSalePrice * v.NQty;
            point += parseInt(v.NCalcOrangeCartPoint);
        }
        */
        $('#cart-price-' + v.VCartid).text(SetNumComma(price));
        if (!isEmpty(v.VNonmemberYn) && v.VNonmemberYn == 'Y') {
            $('#cart-point-' + v.VCartid).text('0P');
        } else {
            $('#cart-point-' + v.VCartid).text(SetNumComma(point) + 'P');
        }
    });
}

function calculateDeliveryProductPrice(obj) {
    const product = obj.product[0];
    // console.log(obj);
    const vProductcd = product.VProductcd;
    const vGproductcd = product.VGproductcd;
    const vUproductcd = product.VUproductcdCart;
    const vDlvGroupCd = product.VDlvGroupCd;
    const vDlvChargeCd = product.VDlvChargeCd;
    const vAddProdYn = product.VAddProdYn;
    const id = vGproductcd + vDlvGroupCd + vDlvChargeCd + vAddProdYn;
    let totalProductPrice = 0;
    $('#delivery-product-' + id + ' .price .num').each(function (i, v) {
        totalProductPrice += fnOnlyNumber($(this).text()).number;
    });
    $('#delivery-product-' + id + ' .bottom-price-box .num:eq(0)').text(SetNumComma(totalProductPrice));

    const discountPrice = fnOnlyNumber($('#delivery-group-' + id + ' .bottom-price-box .num:eq(1)').text()).number;
    const numTotal = totalProductPrice - discountPrice;
    $('#delivery-product-' + id + ' .bottom-price-box .num-total').text(SetNumComma(numTotal));
}

function checkFreeDelivery(vDlvGroupCd) {

    $('#delivery-group-' + vDlvGroupCd + ' .right-dlv-info').children().hide();

    const vExpressYn = $('#delivery-group-' + vDlvGroupCd).data('vExpressYn');
    let groupFreeDeliveryPrice = parseInt($('#delivery-group-' + vDlvGroupCd).data('freeDeliveryPrice'));
    const vDlvChargeCd = $('#delivery-group-' + vDlvGroupCd).data('vDlvChargeCd').toString();
    const vPaidDeliveryYn = $('#delivery-group-' + vDlvGroupCd).data('vPaidDeliveryYn').toString();
    let groupProductPrice = 0;
    let groupProductDlvPrice = 0;
    // console.log('checkFreeDelivery----');
    //console.log('vDlvGroupCd :', vDlvGroupCd);
    //console.log('vDlvChargeCd :', vDlvChargeCd);
    $('#delivery-group-' + vDlvGroupCd + ' .productCheckbox:checked').each(function (i, v) {
        const vProductcd = this.dataset.vProductcd;
        const vGproductcd = this.dataset.vGproductcd;
        const vDlvGroupCd = this.dataset.vDlvGroupCd;
        const vAddProdYn = this.dataset.vAddProdYn;
        const id = vGproductcd + vDlvGroupCd + vAddProdYn;
        //console.log('#delivery-product-' + id);
        $('#delivery-product-' + id + ' .price .num').each(function (j, vv) {
            const price = fnOnlyNumber($(this).text()).number;
            groupProductPrice += price;
        });
        const nDlvCharge = fnOnlyNumber(this.dataset.nDlvCharge).number;
        const nQty = fnOnlyNumber(this.dataset.nQty).number;
        if (vDlvChargeCd == '20' || vDlvChargeCd == '30'){
            if (vDlvChargeCd == '20'){
                groupProductDlvPrice = groupProductDlvPrice + (nDlvCharge * nQty);
            }else{
                groupProductDlvPrice = groupProductDlvPrice + nDlvCharge;
            }
        }
    });
    // console.log(':: groupProductDlvPrice-1 : ' + groupProductDlvPrice);

    const morePrice = groupFreeDeliveryPrice - groupProductPrice;

    if (vExpressYn == "Y") {
        checkExpressFreeDelivery(vDlvGroupCd);
    } else {
        checkFreeDeliveryDefaultData(vDlvGroupCd, vDlvChargeCd, morePrice, groupFreeDeliveryPrice, groupProductPrice, vPaidDeliveryYn);
    }
}

function checkExpressFreeDelivery(vDlvGroupCd) {

    const vExpressYn = $('#delivery-group-' + vDlvGroupCd).data('vExpressYn');
    let groupFreeDeliveryPrice = parseInt($('#delivery-group-' + vDlvGroupCd).data('freeDeliveryPrice'));
    const vDlvChargeCd = $('#delivery-group-' + vDlvGroupCd).data('vDlvChargeCd').toString();
    let groupProductPrice = 0;
    let expressGroupProductPrice = 0;
    const expressGroupListTemp = [];
    const expressCheckGroupListTemp = [];
    var targetDlvGroupCd = "";

    // 특급배송+한팩 체크관계없이 전체 목록
    $('.cart-list-area').each(function (i, v) {
        const deliveryType = this.dataset.deliveryType;
        const vDlvGroupCd = this.dataset.vDlvGroupCd;
        const vDlvChargeCd = this.dataset.vDlvChargeCd;
        const vExpressYn = this.dataset.vExpressYn;

        if (deliveryType == 'E') {
            // 특급+원팩의 묶음 배송그룹의 대표배송그룹
            targetDlvGroupCd = vDlvGroupCd + vDlvChargeCd;
            groupFreeDeliveryPrice = parseInt(this.dataset.freeDeliveryPrice);
        }

        if (vExpressYn == 'Y'){
            expressGroupListTemp[expressGroupListTemp.length] = vDlvGroupCd + vDlvChargeCd;
        }
    });

    $('.cart-list-area .productCheckbox:checked').each(function (i, v) {
        const vProductcd = this.dataset.vProductcd;
        const vGproductcd = this.dataset.vGproductcd;
        const vDlvGroupCd = this.dataset.vDlvGroupCd;
        const vAddProdYn = this.dataset.vAddProdYn;
        const id = vGproductcd + vDlvGroupCd + vAddProdYn;
        $('.delivery-product-' + id + 'Y .price .num').each(function (j, vv) {
            const price = fnOnlyNumber($(this).text()).number;
            expressGroupProductPrice += price;
            expressCheckGroupListTemp[expressCheckGroupListTemp.length] = vDlvGroupCd;
        });
    });
    groupProductPrice = expressGroupProductPrice;

    const morePrice = groupFreeDeliveryPrice - groupProductPrice;

    const expressGroupList = getUnique(expressGroupListTemp);
    const expressCheckGroupList = getUnique(expressCheckGroupListTemp);

    if (expressGroupList.length > 1) {
        // 특급배송인 경우 배송그룹이 한팩과묶여 다건의 그룹이 발생될 수 있기 때문에 합산된 정보로 계산 및 노출

        if (expressCheckGroupList.indexOf(targetDlvGroupCd) > -1) {
            $('#delivery-group-' + targetDlvGroupCd + ' .right-dlv-info .more-delivery-price').text(SetNumComma(morePrice));
            if (groupFreeDeliveryPrice <= groupProductPrice) {
                checkFreeDeliveryDefaultDataNone(targetDlvGroupCd);
            } else {
                checkFreeDeliveryDefaultDataBlock(targetDlvGroupCd);
            }

            for (var i = 0; i < expressCheckGroupList.length; i++) {
                if (expressCheckGroupList[i] != targetDlvGroupCd) {
                    checkFreeDeliveryDefaultDataNone(expressCheckGroupList[i]);
                }
            }


        } else {
            var targetDlvGroupCdSub = expressCheckGroupList[0];
            $('#delivery-group-' + targetDlvGroupCdSub + ' .right-dlv-info .more-delivery-price').text(SetNumComma(morePrice));
            if (groupFreeDeliveryPrice <= groupProductPrice) {
                $('#delivery-group-' + targetDlvGroupCdSub + ' input[name=calcDeliveryPrice]').val(0);
                $('#span-dlv-price-' + targetDlvGroupCdSub).text(SetNumComma(0));
                $('#delivery-group-' + targetDlvGroupCdSub + ' .right-dlv-info .dlvFree').show();
                $('#delivery-group-' + targetDlvGroupCdSub + ' .dlv-guide').css('display', 'none');

            } else {
                const defaultDeliveryPrice = $('#delivery-group-' + targetDlvGroupCd + ' input[name=defaultDeliveryPrice]').val();
                $('#delivery-group-' + targetDlvGroupCdSub + ' input[name=calcDeliveryPrice]').val(defaultDeliveryPrice);
                $('#span-dlv-price-' + targetDlvGroupCdSub).text(SetNumComma(defaultDeliveryPrice));
                $('#delivery-group-' + targetDlvGroupCdSub + ' .right-dlv-info .dlvNotFree').show();
                $('#delivery-group-' + targetDlvGroupCdSub + ' .dlv-guide').text(SetNumComma(groupFreeDeliveryPrice) + '원 이상 무료배송');
                $('#delivery-group-' + targetDlvGroupCdSub + ' .dlv-guide').css('display', 'inline-block');
            }
            for (var i = 0; i < expressCheckGroupList.length; i++) {
                if (expressCheckGroupList[i] != targetDlvGroupCdSub) {
                    checkFreeDeliveryDefaultDataNone(expressCheckGroupList[i]);
                }
            }
        }

        for (var i = 0; i < expressGroupList.length; i++) {
            if (expressCheckGroupList.indexOf(expressGroupList[i]) < 0) {
                $('#delivery-group-' + expressGroupList[i] + ' .right-dlv-info').children().hide();
            }
        }

    } else {
        // 특급배송이 아닌 일반 배송그룹에 대해서는 각 개별의 단일배송그룹에 대해서 정보를 계산 및 노출
        checkFreeDeliveryDefaultData(vDlvGroupCd, vDlvChargeCd, morePrice, groupFreeDeliveryPrice, groupProductPrice);
    }
}

function checkFreeDeliveryDefaultData(vDlvGroupCd, vDlvChargeCd, morePrice, groupFreeDeliveryPrice, groupProductPrice, vPaidDeliveryYn) {
    if(vPaidDeliveryYn != 'Y') {
        $('#delivery-group-' + vDlvGroupCd + ' .right-dlv-info .more-delivery-price').text(SetNumComma(morePrice));
    }

    if (groupFreeDeliveryPrice <= groupProductPrice && vPaidDeliveryYn != 'Y') {
        checkFreeDeliveryDefaultDataNone(vDlvGroupCd);
    } else {
        checkFreeDeliveryDefaultDataBlock(vDlvGroupCd);
    }

    if (vDlvChargeCd == '20' || vDlvChargeCd == '30') {
        $('#delivery-group-' + vDlvGroupCd + ' .right-dlv-info').children().hide();
        checkFreeDeliveryDefaultDataBlock(vDlvGroupCd);
    }
}


function checkFreeDeliveryDefaultDataNone(vDlvGroupCd) {
    $('#delivery-group-' + vDlvGroupCd + ' input[name=calcDeliveryPrice]').val(0);
    $('#delivery-group-' + vDlvGroupCd + ' .right-dlv-info .dlvFree').show();
    $('#delivery-group-' + vDlvGroupCd + ' .dlv-guide').css('display', 'none');
}


function checkFreeDeliveryDefaultDataBlock(vDlvGroupCd) {
    const defaultDeliveryPrice = $('#delivery-group-' + vDlvGroupCd + ' input[name=defaultDeliveryPrice]').val();
    $('#delivery-group-' + vDlvGroupCd + ' input[name=calcDeliveryPrice]').val(defaultDeliveryPrice);
    $('#delivery-group-' + vDlvGroupCd + ' .right-dlv-info .dlvNotFree').show();
    $('#delivery-group-' + vDlvGroupCd + ' .dlv-guide').css('display', 'inline-block');
}

function calculateTotalOrderPrice() {

    function calcProductPrice() {
        // console.log('calcProductPrice --- ');
        let totalPrice = 0;
        $('.productCheckbox:checked').each(function (i, v) {
            const vProductcd = this.dataset.vProductcd;
            const vGproductcd = this.dataset.vGproductcd;
            const vDlvGroupCd = this.dataset.vDlvGroupCd;
            const vAddProdYn = this.dataset.vAddProdYn;
            const id = vGproductcd + vDlvGroupCd + vAddProdYn;
            // console.log('#delivery-product-' + id);
            let groupPrice = 0;
            $('#delivery-product-' + id + ' .price .num').each(function (j, vv) {
                const price = fnOnlyNumber($(this).text()).number;
                // console.log('price =', price);
                // 품절상태
                const outOfStockStatus = $(this).data('out-of-stock-status');

                if (outOfStockStatus == 'N') {
                    totalPrice += price;
                    groupPrice += price;
                }

            });
            // console.log('#delivery-product-' + id + ' totalPrice =', groupPrice);
            $('#delivery-product-' + id + ' .bottom-price-box .num:eq(0)').text(SetNumComma(groupPrice));
            const discountPrice = fnOnlyNumber($('#delivery-product-' + id + ' .bottom-price-box .num:eq(1)').text()).number;
            const numTotal = groupPrice - discountPrice;
            // console.log('groupPrice =', groupPrice);
            // console.log('discountPrice =', discountPrice);
            // console.log('numTotal =', numTotal);
            $('#delivery-product-' + id + ' .bottom-price-box .num-total').text(SetNumComma(numTotal));
        });
        return totalPrice;
    }

    function calcProductPoint() {
        let productPoint = 0;
        let orangePoint = 0;
        let orangeMinus = 0;
        // console.log('calcProductPoint------');
        $('.productCheckbox:checked').each(function (i, v) {
            const vProductcd = this.dataset.vProductcd;
            const vGproductcd = this.dataset.vGproductcd;
            const vDlvGroupCd = this.dataset.vDlvGroupCd;
            const vAddProdYn = this.dataset.vAddProdYn;
            const id = vGproductcd + vDlvGroupCd + vAddProdYn;

            $('#delivery-product-' + id + ' .nSavePoint').each(function (j, vv) {
                const point = parseInt($(this).val());

                // 품절상태
                const outOfStockStatus = $(this).data('out-of-stock-status');

                if (outOfStockStatus == 'N') {
                    productPoint += point;
                }

            });

            $('#delivery-product-' + id + ' .nOrangeSavePoint').each(function (j, vv) {
                const point = parseInt($(this).val());

                // 품절상태
                const outOfStockStatus = $(this).data('out-of-stock-status');

                if (outOfStockStatus == 'N') {
                    orangePoint += point;
                }

            });

            $('#delivery-product-' + id + ' .nOrangeSavePriceMinus').each(function (j, vv) {
                const point = parseInt($(this).val());

                // 품절상태
                const outOfStockStatus = $(this).data('out-of-stock-status');
                if (outOfStockStatus == 'N') {
                    orangeMinus += point;
                }

            });
        });
        const data = {
            productPoint: productPoint,
            orangePoint: orangePoint,
            orangeMinus: orangeMinus
        }
        return data;
    }

    function calcDiscountPrice() {
        // console.log('calcDiscountPrice-----');
        let totalDiscount = 0;
        $('.productCheckbox:checked').each(function (i, v) {
            const vProductcd = this.dataset.vProductcd;
            const vGproductcd = this.dataset.vGproductcd;
            const vDlvGroupCd = this.dataset.vDlvGroupCd;
            const vAddProdYn = this.dataset.vAddProdYn;
            const id = vGproductcd + vDlvGroupCd + vAddProdYn;
            // console.log('#delivery-product-' + id);
            $('#delivery-product-' + id + ' .bottom-price-box .discount').each(function (k, vv) {
                const discount = fnOnlyNumber($(this).text()).number;
                totalDiscount += discount;
            });
        });
        return totalDiscount;
    }

    function calcDeliveryPrice() {
        const vDlvGroupCdListTemp = [];
        $('.productCheckbox:checked').each(function (i, v) {
            vDlvGroupCdListTemp.push(this.dataset.vDlvGroupCd);
        });
        //const vDlvGroupCdList = Array.from(new Set(vDlvGroupCdListTemp));
        const vDlvGroupCdList = getUnique(vDlvGroupCdListTemp);

        const dlvGrpMap = dlvMap;
        // console.log('calcDeliveryPrice------');
        // console.log(dlvGrpMap);
        let isOnePackIncluded = false;
        let onePackDlvGrpCd = '';
        let nFreeDlvPrice = 0;
        $.each(dlvGrpMap, function (dlvGrpCd, dlvGrpInfo) {
            const isChecked = $('#delivery-group-' + dlvGrpCd + ' .checkboxGroup').is(':checked');
            if (dlvGrpInfo.vOnePackYn == 'Y' && isChecked) {
                isOnePackIncluded = true;
                onePackDlvGrpCd = dlvGrpCd;
                nFreeDlvPrice = dlvGrpInfo.nFreeDlvPrice;
            }
        });

        let isExpressDlvIncluded = false;
        $.each(dlvGrpMap, function (dlvGrpCd, dlvGrpInfo) {
            const isChecked = $('#delivery-group-' + dlvGrpCd + ' .checkboxGroup').is(':checked');
            if (dlvGrpInfo.vExpDlvYn == 'Y' && dlvGrpInfo.vOnePackYn == 'N' && isChecked) {
                isExpressDlvIncluded = true;
            }
        });

        if (isOnePackIncluded && isExpressDlvIncluded) {
            //$('#delivery-group-' + onePackDlvGrpCd).data('freeDeliveryPrice', 0);
        } else {
            $('#delivery-group-' + onePackDlvGrpCd).data('freeDeliveryPrice', nFreeDlvPrice);
        }

        $.each(vDlvGroupCdList, function (i, vDlvGroupCd) {
            checkFreeDelivery(vDlvGroupCd);
        });

        let totalDeliveryPrice = 0;
        $.each(vDlvGroupCdList, function (i, vDlvGroupCd) {
            totalDeliveryPrice += fnOnlyNumber($('#delivery-group-' + vDlvGroupCd + ' input[name=calcDeliveryPrice]').val()).number;
        });

        return totalDeliveryPrice;
    }

    function setPricePoint(obj) {

        const isOrange = obj.isOrange;
        let totalOrangeDiscount = 0;
        $(obj.orderProductList).each(function (i, product) {

            const vCartid = product.VCartid;

            let calcSalePrice = product.NCalcCartSalePrice;
            let calcSavePoint = parseInt(product.NCalcCartPoint);
            /*
            if (isOrange) {
                calcSalePrice = product.NCalcOrangeCartSalePrice * product.NQty;
                calcSavePoint += parseInt(product.NCalcOrangeCartPoint);
                totalOrangeDiscount += parseInt(product.NMinusOrange);
            }
            */

            if ($('#cart-price-' + vCartid).data('out-of-stock-status') == 'Y') {
                calcSalePrice = 0;
            }

            if ($('#cart-point-' + vCartid).data('out-of-stock-status') == 'Y') {
                calcSavePoint = 0;
            }

            $('#cart-price-' + vCartid).text(SetNumComma(calcSalePrice));
            $('#cart-point-' + vCartid).text(SetNumComma(calcSavePoint) + 'P');
            $('#cart-save-point-' + vCartid).val(product.NCalcCartPoint);
            $('#cart-orange-save-point-' + vCartid).val(product.NCalcOrangeCartPoint);
            $('#cart-orange-save-price-minus-' + vCartid).val(product.NMinusOrange);
            $('#cart-orange-save-price-' + vCartid).val(product.NCalcOrangeCartSalePrice * product.NQty);

        });
        // console.log('totalOrangeDiscount =', totalOrangeDiscount);
    }

    cmAjax({
        url: '/order/cart/getUserCartDeliveryGroup'
        , type: 'POST'
        , dataType: "json"
        , success: function (data) {
            dlvMap = data.dlvMap;

            setPricePoint(data);

            const totalProductPrice = calcProductPrice();
            const totalProductPoint = calcProductPoint();
            const totalDiscountPrice = calcDiscountPrice();
            const totalDeliveryPrice = calcDeliveryPrice();

            const totalOrderPrice = totalProductPrice - totalDiscountPrice + totalDeliveryPrice;

            $('#totalProductPrice').text(SetNumComma(totalProductPrice));
            $('#totalProductPoint').text(SetNumComma(totalProductPoint.productPoint));
            $('#orangePoint').text(SetNumComma(totalProductPoint.orangePoint));
            $('#totalDiscountPrice').text(SetNumComma(totalDiscountPrice));
            $('#totalDeliveryPrice').text(SetNumComma(totalDeliveryPrice));
            $('.totalOrderPrice').text(SetNumComma(totalOrderPrice));
            $('.orangeMemberSavePoint').text(SetNumComma(totalProductPoint.productPoint + totalProductPoint.orangePoint));

            $('div.orangeMembers-skin-banner').parent().hide();
            if(totalProductPoint.orangePoint > 0 && totalProductPoint.orangeMinus > 0) {
                $('div.orangeMembers-skin-banner').parent().show();
                $('#obanner-d-text').show();
                $('#obanner-or-text').show();
                $('#obanner-p-text').show();
                $("#obanner-price-minus").css("color","#cccccc");
            } else if(totalProductPoint.orangePoint > 0){
                $('div.orangeMembers-skin-banner').parent().show();
                $('#obanner-d-text').hide();
                $('#obanner-or-text').hide();
                $('#obanner-p-text').show();
            } else if(totalProductPoint.orangeMinus > 0) {
                $('div.orangeMembers-skin-banner').parent().show();
                $('#obanner-d-text').show();
                $('#obanner-or-text').hide();
                $('#obanner-p-text').hide();
                $("#obanner-price-minus").css("color","#ff7001");
            }

            $('#obanner-point').text(SetNumComma(totalProductPoint.orangePoint)+'P');
            $('#obanner-price-minus').text(SetNumComma(totalProductPoint.orangeMinus)+'원');
            console.log(data.isOrange)
            if(data.isOrange) {
                $('#buttonOrangeMemberPop').off('click');
                $('#order').off('click');
                if (totalProductPoint.orangePoint <= 0 && totalProductPoint.orangeMinus <= 0) {
                    $('#buttonOrangeMemberPop').on('click', order);
                    $('input[name="orangeMembersBenefit"]').val('N');
                } else {
                    $('#order').on('click', order);
                    $('#buttonOrangeMemberPop').on('click', calculateOrangeMember);
                    layerPop();
                }
            }

            $('#radioOPoint').hide();
            $('#radioODiscount').hide();
            $('#radioOPoint .custom-radio.point').removeClass("hide");
            $('#radioODiscount .custom-radio.discount').removeClass("hide");
            if(totalProductPoint.orangePoint > 0 ) {
                $('#radioOPoint').show();
                if(totalProductPoint.orangeMinus <= 0) $('#radioOPoint .custom-radio.point').addClass("hide");
            }
            if (totalProductPoint.orangeMinus > 0) {
                if(totalProductPoint.orangePoint <= 0) $('#radioODiscount .custom-radio.discount').addClass("hide");
                $('#radioODiscount').show();
            }

            if(totalProductPoint.orangePoint > 0 && totalProductPoint.orangeMinus > 0){
                $('#orangeMemberPop .info-text').removeClass('hide');
            } else {
                $('#orangeMemberPop .info-text').addClass('hide');
            }
        }
        , error: function (e) {
            console.log(e);
        }
    });

}

function checkAll(e) {
    const checked = $('#checkAll').is(':checked');
    if (checked) {
        // wnsgml351 인수인계 추가 - disabled 제외
        $('.cart-list-area .checkbox:not(:disabled)').prop('checked', true)
        $(this).next('label').text('전체해제')
    } else {
        $('.cart-list-area .checkbox').prop('checked', false);
        $(this).next('label').text('전체선택')
    }
    calculateTotalOrderPrice();
}

function checkDeliveryGroup(e) {
    const checked = $(this).is(':checked');
    const name = $(this).attr('name');
    if (checked) {
        $('input[name=' + name + ']:not(:disabled)').prop('checked', true);
    } else {
        $('input[name=' + name + ']:not(:disabled)').prop('checked', false);
    }
    calculateTotalOrderPrice();
}

function checkboxFunc(e) {
    const name = $(this).attr('name');
    const groupCount = $('input[name=' + name + ']:not(.checkboxGroup):not(:disabled)').length
    const checkedCount = $('input[name=' + name + ']:not(.checkboxGroup):checked').length
    if(name == 'routineDlvYn') {
        return; // 상품상세 > 정기배송신청하기 checkbox
    }
    if (checkedCount == groupCount) {
        $('.checkboxGroup[name=' + name + ']').prop('checked', true);
    } else {
        $('.checkboxGroup[name=' + name + ']').prop('checked', false);
    }

    const checkboxLength = $('.cart-list-area input[type=checkbox]:not(:disabled)').length
    const checkedLength = $('.cart-list-area input[type=checkbox]:checked:not(:disabled)').length;

    if (checkedLength < checkboxLength){
        $('#checkAll').prop('checked', false);
        $('#checkAll').next('label').text('전체선택');
    } else if (checkedLength == checkboxLength){
        $('#checkAll').prop('checked', true);
        $('#checkAll').next('label').text('전체해제');
    }

    calculateTotalOrderPrice();
}

function deleteUserCart(e) {
    const deleteUserCartList = [];
    const data = {
        vCartid: encodeURIComponent(this.dataset.vCartid),
        vProductcd: encodeURIComponent(this.dataset.vProductcd),
        vUproductcd: encodeURIComponent(this.dataset.vUproductcdCart),
        vGproductcd: encodeURIComponent(this.dataset.vGproductcd),
        deleteType: 'ONE'
    }
    deleteUserCartList.push(data);

    callAjax('/order/cart/deleteUserCartAjax', deleteUserCartList);
}

function deleteUserCartList(e) {

    const deleteUserCartList = [];
    $('.deleteUserCartList:checked').each(function (i, v) {
        const data = {
            vCartid: encodeURIComponent(this.dataset.vCartid),
            vProductcd: encodeURIComponent(this.dataset.vProductcd),
            vUproductcd: encodeURIComponent(this.dataset.vUproductcdCart),
            vGproductcd: encodeURIComponent(this.dataset.vGproductcd),
            deleteType: 'ALL'
        }
        deleteUserCartList.push(data);
    });

    if (deleteUserCartList.length === 0) {
        alert('삭제할 상품을 선택해주세요.');
        return;
    }

    if (confirm('선택한 상품을 장바구니에서 삭제하시겠습니까?')) {
        callAjax('/order/cart/deleteUserCartAjax', deleteUserCartList);
    }

}

function callAjax(url, obj) {
    // console.log(obj);
    cmAjax({
        url: url
        , type: 'POST'
        , data: {
            dataList: JSON.stringify(obj)
        }
        , dataType: "json"
        , success: function (data) {
            if (data.status == 'succ') {
                window.location.href = '/order/cart'
            } else {
                if (data.status == 'fail' && !isEmpty(data.message)) {
                    alert(data.message);
                }
            }
        }
        , error: function (e) {
            console.log(e);
        }
    });
}

function getProductCoupon(e) {
    const vUproductcd = this.dataset.vUproductcd;
    const vGproductcd = this.dataset.vGproductcd;
    const vProductcd = this.dataset.vProductcd;
    const vAddProdYn = this.dataset.vAddProdYn;
    // console.log('func getProductCoupon---');
    // console.log(this.dataset);

    let nSalePrice = 0;
    const id = $(this).closest('div.bottom-price-box').parent('li').attr('id');
    $('#productCouponForm input[name=vProductcdArr]').val('');
    // console.log('id ', id);
    const moProductcd = $(this).closest('div.bottom-price-box').parent('li').data('vProductcd');
    const moUproductcd = $(this).closest('div.bottom-price-box').parent('li').data('vUproductcd');
    const moGproductcd = $(this).closest('div.bottom-price-box').parent('li').data('vGproductcd');
    const moAddProdYn = $(this).closest('div.bottom-price-box').parent('li').data('vAddProdYn');
    // console.log('모상품?', moAddProdYn == 'N');
    // console.log('moGproductcd ?', moGproductcd);
    if (moAddProdYn == 'Y') {
        alert('부속상품은 쿠폰 적용 대상이 아닙니다.');
        return;
    }

    let input = '';
    $('.vProductcd-' + vGproductcd).each(function (i, v) {
        const vAddProdYn = this.dataset.vAddProdYn;
        // console.log('vAddProdYn ?', vAddProdYn);
        input = '<input type="hidden" name="vProductcdArr" value="' + v.value + '" />';
        $('#productCouponForm').append(input);
    });

    $('#productCouponForm')
        .append('<input type="hidden" name="vGproductcd" value="' + vGproductcd + '" />')
        .append('<input type="hidden" name="vUproductcd" value="' + vUproductcd + '" />')
        .append('<input type="hidden" name="vProductcd" value="' + vProductcd + '" />');

    cmAjax({
        url: '/order/cart/getProductCoupon'
        , type: "GET"
        , data: $('#productCouponForm').serialize()
        , success: function (data) {
            if (data.userCouponList.length == 0) {
                alert('사용할 수 있는 쿠폰이 없습니다.');
            } else {
                // console.log(data);
                data.vGproductcd = vGproductcd;
                data.vProductcd = vProductcd;
                data.vUproductcd = vUproductcd;
                data.liId = id;
                setCoupon(data);
                setPrice(data);
                $('#popup-product-coupon').show();
            }
            $('#productCouponForm input:not(:first)').remove();
        }
        , error: function (e) {
            console.log('e:' + JSON.stringify(e));
        }
    });

    function setPrice(data) {

        let productPrice = 0;
        const isOrange = data.isOrange;

        $(data.cartProductList).each(function (i, product) {
            /*
            if (isOrange) {
                productPrice += product.NCalcOrangeCartSalePrice * product.NQty;
            } else {
                productPrice += product.NCalcCartSalePrice;
            }
            */
            productPrice += product.NCalcCartSalePrice;
        });

        $('#nPrice').text(SetNumComma(productPrice));
        $('#nSalePrice').text(SetNumComma(nSalePrice));

        let calcPrice = productPrice - nSalePrice;
        if (calcPrice < 0) {
            calcPrice = 0;
        }
        $('#calcPrice').text(SetNumComma(calcPrice));
    }

    function setCoupon(data) {
        // console.log('setCoupon ----');
        // console.log(data);
        let usableCoupon = 0;
        let couponElement = [];

        couponElement.push('<li class="box-item">');
        couponElement.push('   <div class="custom-radio">');
        couponElement.push('       <input type="radio" id="radio-00" checked=""');
        couponElement.push('               data-v-sale-type="N" ');
        couponElement.push('               data-v-productcd="' + data.vProductcd + '" ');
        couponElement.push('               data-v-uproductcd="' + data.vUproductcd + '" ');
        couponElement.push('               data-v-gproductcd="' + vGproductcd + '" ');
        couponElement.push('               data-v-li-id="' + data.liId + '" ');
        couponElement.push('               class="radio type-box" name="vCouponCd">');
        couponElement.push('       <label for="radio-00">');
        couponElement.push('           <span class="normal">적용안함</span>');
        couponElement.push('       </label>');
        couponElement.push('   </div>');
        couponElement.push('</li>');

        $.each(data.userCouponList, function (i, v) {
            couponElement.push('<li class="box-item">');
            couponElement.push('    <div class="custom-radio">');
            couponElement.push('        <input type="radio" id="radio-' + i + '-' + v.VCouponCd + '" ');
            couponElement.push('               data-v-coupon-cd="' + v.VCouponCd + '" ');
            couponElement.push('               data-v-coupon-type="' + v.VCouponType + '" ');
            couponElement.push('               data-v-coupon-type-nm="' + v.VCouponTypeNm + '" ');
            couponElement.push('               data-v-prt-edtm="' + v.VPrtEdtm + '" ');
            couponElement.push('               data-v-prt-stdtm="' + v.VPrtSdtm + '" ');
            couponElement.push('               data-v-rest-date="' + v.VRestDate + '" ');
            couponElement.push('               data-v-sale-type="' + v.VSaleType + '" ');
            couponElement.push('               data-n-sale-price="' + v.NSalePrice + '" ');
            couponElement.push('               data-n-sale-rate="' + v.NSaleRate + '" ');
            couponElement.push('               data-v-sale-value="' + v.VSaleValue + '" ');
            couponElement.push('               data-v-status="' + v.VStatus + '" ');
            couponElement.push('               data-v-status-nm="' + v.VStatusNm + '" ');
            couponElement.push('               data-v-user-cart-coupon-id="' + v.VUserCartCouponId + '" ');
            couponElement.push('               data-n-max-sale-amt="' + v.NMaxSaleAmt + '" ');
            couponElement.push('               data-v-sale-type-nm="' + v.VSaleTypeNm + '" ');
            couponElement.push('               data-n-min-order-amt="' + v.NMinOrderAmt + '" ');
            couponElement.push('               data-n-min-order-qty="' + v.NMinOrderQty + '" ');
            couponElement.push('               data-v-coverage-type="' + v.VCoverageType + '" ');
            couponElement.push('               data-v-coverage-type-nm="' + v.VCoverageTypeNm + '" ');
            couponElement.push('               data-v-productcd="' + v.VProductcd + '" ');
            couponElement.push('               data-v-uproductcd="' + data.vUproductcd + '" ');
            couponElement.push('               data-v-gproductcd="' + vGproductcd + '" ');
            couponElement.push('               data-v-brand-cd="' + v.VBrandCd + '" ');
            couponElement.push('               data-v-usable-yn="' + v.VUsableYn + '" ');
            couponElement.push('               data-v-rest-date="' + v.VRestDate + '" ');
            couponElement.push('               data-v-li-id="' + data.liId + '" ');
            if (!isEmpty(v.VUserCartCouponId) && (v.VUproductcd == data.vGproductcd)) {
                couponElement.push('               checked=""');
                nSalePrice = v.NCouponPrice;
            }
            if (!isEmpty(v.VUproductcd)) {
                couponElement.push('               disabled');
            }
            couponElement.push('               class="radio type-box" name="vCouponCd">');
            couponElement.push('        <label for="radio-' + i + '-' + v.VCouponCd + '">');

            if (v.VSaleType == '100') {
                couponElement.push('            <span class="sale">' + SetNumComma(v.NSalePrice) + '원</span>');
            } else if (v.VSaleType == '200') {
                couponElement.push('            <span class="sale">' + v.NSaleRate + '%</span>');
            } else {
                couponElement.push('            <span class="sale">' + v.VSaleValue + '</span>');
            }

            couponElement.push('            <span class="tit">' + v.VCouponnm + '</span>');
            if (!isEmpty(v.VCondition)) {
                couponElement.push('            <span class="desc">' + v.VCondition + '</span>');
            }
            if (parseInt(v.NMinOrderAmt) > 0) {
                couponElement.push('            <span class="desc"> 최소 결제금액 : ' + SetNumComma(v.NMinOrderAmt) + '원</span>');
            }
            if (parseInt(v.NMaxSaleAmt) > 0) {
                couponElement.push('            <span class="desc"> 최대 할인금액 : ' + SetNumComma(v.NMaxSaleAmt) + '원</span>');
            }
            if (parseInt(v.NMinOrderQty) > 0) {
                couponElement.push('            <span class="desc"> 최소 구매수량 : ' + SetNumComma(v.NMinOrderQty) + '개</span>');
            }
            if (!isEmpty(v.VEndDtm)) {
                couponElement.push('            <span class="date">' + v.VEndDtm + ' 까지</span>');
            }
            couponElement.push('        </label>');
            couponElement.push('    </div>');
            couponElement.push('</li>');
            if (v.VUsableYn == 'Y') {
                usableCoupon++;
            }
        })

        $('#couponList').html(couponElement.join(''));
    }

}

function calcDiscount(e) {
    const obj = this.dataset;
    // console.log('func calcDiscount---');
    // console.log(obj);
    const nPrice = fnOnlyNumber($('#nPrice').text()).number;

    const vProductcd = obj.vProductcd;
    const vUproductcd = obj.vUproductcd;
    const vGproductcd = obj.vGproductcd;
    const id = obj.vLiId;

    let nProductPrice = 0;
    $('#' + id + ' .price-div .cart-price-' + vUproductcd).each(function (i, v) {
        nProductPrice += fnOnlyNumber($(this).text()).number;
    });

    if (nProductPrice === 0) {
        $('#nSalePrice').text(0);
        $('#calcPrice').text(SetNumComma(nPrice));
        return;
    }

    const nQty = $('.input-qty.product-' + obj.vProductcd).val();
    const vCouponCd = obj.vCouponCd;
    const nMaxSaleAmt = parseInt(obj.nMaxSaleAmt);
    const nMinOrderAmt = parseInt(obj.nMinOrderAmt);
    const nMinOrderQty = parseInt(obj.nMinOrderQty);
    let nSalePrice = 0;
    let calcPrice = 0;

    if (obj.vSaleType == '200' && nMaxSaleAmt == 0) {
        alert('[dev]\n정률 쿠폰인데 최대할인금액 값이 없습니다.\n해당 쿠폰을 새로 등록해주세요.\n쿠폰코드 : ' + vCouponCd);
        $('input[name=vCouponCd]:first').click();
        return;
    }

    if (obj.vSaleType == 'N') {
        nSalePrice = 0;
        calcPrice = nProductPrice;
        obj.vProductcd = vUproductcd;
        obj.url = '/order/cart/userCartCouponDeleteAjax';
    }

    const arrCartid = [];
    $('.arrCartid-' + vGproductcd).each(function (i, v) {
        arrCartid.push(v.value);
    });
    // console.log('arrCartid');
    // console.log(arrCartid);
    cmAjax({
        url: "/order/order/getCouponAppliedPriceAjax"
        , type: "post"
        , data: {
            vCouponCd: vCouponCd,
            arrCartid: arrCartid,
            vCartType: '01'
        }
        , dataType: "json"
        , success: function (data) {
            // console.log(data);
            if (obj.vSaleType != 'N' && !data.isUsableCoupon) {
                alert(data.reason);
            }
            $('#popup-product-coupon #nSalePrice').text(SetNumComma(data.discount));
            $('#popup-product-coupon #calcPrice').text(SetNumComma(fnOnlyNumber($('#popup-product-coupon #nPrice').text()).number - data.discount));
        }
        , error: function (e) {
            console.log(JSON.stringify(e));
        }
    });

}

function couponApply(e) {

    const obj = $('#popup-product-coupon .radio:checked').data();
    // console.log(obj);
    const coupon = $('#' + obj.vLiId + ' .productCheckbox').data();
    // console.log(coupon);
    if (isEmpty(obj.vCouponCd)) {
        couponApplyCancel();
        return;
    }

    const arrCartid = [];
    $('.arrCartid-' + obj.vGproductcd).each(function (i, v) {
        arrCartid.push(v.value);
    });

    cmAjax({
        url: '/order/cart/couponApplyAjax'
        , type: 'POST'
        , data: {
            vCouponCd: obj.vCouponCd,
            arrCartid: arrCartid,
            vGproductcd: coupon.vGroductcd,
            vUproductcd: coupon.vUproductcdCart,
            vProductcd: coupon.vProductcd,
            vCartType: '01'
        }
        , dataType: "json"
        , success: function (data) {
            // console.log(data);
            if (data.status == 'succ') {
                window.location.href = '/order/cart'
            }
        }
        , error: function (e) {
            console.log(e);
        }
    });

}

function couponApplyCancel() {
    $('input[name=vCouponCd]:first').prop('checked', true);
    // wnsgml351 인수인계 추가 - 적용취소 할때 적용안함 클릭되게 수정
    $('input[name=vCouponCd]:first').click();

    const obj = $('input[name=vCouponCd]:first').data();
    obj.vUproductcd = obj.vProductcd;

    // console.log(obj);

    const data = {};
    $.each(obj, function (name, value) {
        data[name] = encodeURIComponent(value);
    });
    data['vCartType'] = '01';
    data['vGproductcd'] = $('#' + obj.vLiId).data('vGproductcd');
    cmAjax({
        url: '/order/cart/userCartCouponDeleteAjax'
        , type: 'POST'
        , data: {
            data: JSON.stringify(data)
        }
        , dataType: "json"
        , success: function (data) {
            if (data.status == 'succ') {
                window.location.href = '/order/cart'
            }
        }
        , error: function (e) {
            console.log(e);
        }
    });
}

function closePop() {
    $('.resetPrice').text('0');
    $('#popup-product-coupon').hide();
}

function order(e) {
    if ($('.checkbox:checked:not("#checkAll")').length == 0) {
        alert('구매할 상품을 하나 이상 선택해주세요.');
        return;
    }
    // console.log('order-----');
    $("#frm input[name='vProductcdArr']").val('');
    $("#frm input[name='vProductcdArr']:not(:first)").remove();
    let flag = false;
    let islandDlvCnt = 0;
    let outOfStockFlag = false;
    let minMaxQtyCntCheck = false;
    let minMaxQtyCheckMsg = '';
    let nUserPurchaseCountFlag = false;
    let nUserPurchaseCountMsg = '';

    $('.productCheckbox:checked').each(function (i, v) {
        const vIslandDlvYn = this.dataset.vIslandDlvYn;
        const nMinSaleQty = this.dataset.nMinSaleQty;
        const nMaxSaleQty = this.dataset.nMaxSaleQty;
        const vProductcd = this.dataset.vProductcd;
        const vGproductcd = this.dataset.vGproductcd;
        const vDlvGroupCd = this.dataset.vDlvGroupCd;
        const vAddProdYn = this.dataset.vAddProdYn;
        const nSaleLimit = parseInt(this.dataset.nSaleLimit);
        const nUserPurchaseCount = parseInt(this.dataset.nUserPurchaseCount);
        const id = vGproductcd + vDlvGroupCd + vAddProdYn;
        // console.log('vAddProdYn ?', vAddProdYn == 'N'
        //     , 'nSaleLimit =', nSaleLimit
        //     , '0 < nSaleLimit ?', (0 < nSaleLimit)
        //     , 'nUserPurchaseCount =', nUserPurchaseCount
        //     , 'nSaleLimit <= nUserPurchaseCount ?', nSaleLimit <= nUserPurchaseCount);
        if (vAddProdYn == 'N' && 0 < nSaleLimit && nSaleLimit <= nUserPurchaseCount) {
            nUserPurchaseCountFlag = true;
            const nm = $('#delivery-product-' + id).find('.productNm').text().trim();
            nUserPurchaseCountMsg = nm + " 상품은 최대 " + nSaleLimit + "번만 구매가능한 상품입니다.";
        }
        // console.log('nUserPurchaseCountFlag ?', nUserPurchaseCountFlag);
        if (!isEmpty(vGproductcd)) {

            $('#delivery-product-' + id + ' .vProductcd-' + vGproductcd).each(function (i, v) {
                const input = '<input type="hidden" name="vProductcdArr" value="' + v.value + '" />';
                $('#frm').append(input);
            });

            let sumOptionProductQty = 0;
            let productNm = '';

            $('#delivery-product-' + id + ' .input-qty').each(function (j, vv) {
                const productQty = parseInt(vv.value);

                const outOfStockStatus = $(this).data('out-of-stock-status');
                if (outOfStockStatus == 'Y') {
                    outOfStockFlag = true;
                }
                productNm = $('#delivery-product-' + id).find('.productNm').text().trim();

                if (productQty > 0) {
                    sumOptionProductQty += productQty;
                }

            });

            if (nMinSaleQty > 0 && sumOptionProductQty < nMinSaleQty) {
                minMaxQtyCntCheck = true;
                minMaxQtyCheckMsg = productNm + " 상품은 최소 " + nMinSaleQty + "개 이상 구매하셔야 합니다.";
            }

            if (nMaxSaleQty > 0 && sumOptionProductQty > nMaxSaleQty) {
                minMaxQtyCntCheck = true;
                minMaxQtyCheckMsg = productNm + " 상품은 최대 " + nMaxSaleQty + "개까지만 구매하실 수 있습니다.";
            }

        }
        if (vIslandDlvYn == 'Y') {
            flag = true;
            islandDlvCnt++;
        }
    });

    let islandNotDlvProductnm = '';
    if (flag) {
        let islandDlvProductCnt = 0;
        $('.productCheckbox:checked').each(function (i, v) {
            const vIslandDlvYn = this.dataset.vIslandDlvYn;
            if (vIslandDlvYn == 'Y') {
                islandDlvProductCnt++;
            } else {
                islandNotDlvProductnm = this.dataset.vProductnm;
            }
        });
        if (islandDlvCnt !== $('.productCheckbox:checked').length) {

            let msg = '';
            if (isEmpty(islandNotDlvProductnm)) {
                msg = '도서산간 배송 불가한 상품과 같이 주문할 수 없습니다.';
            } else {
                msg = islandNotDlvProductnm + ' 은(는) 도서산간 배송 불가한 상품으로 같이 주문할 수 없습니다.';
            }

            // alert(msg);
            // return;
        }
    }

    // wnsgml351 인수인계 추가 - 품절 상품 주문 못하는 기능 추가
    if (outOfStockFlag) {
        alert('품절인 상품과 같이 주문할 수 없습니다.');
        return;
    }

    // wnsgml351 인수인계 추가
    // 수량체크여부
    if (minMaxQtyCntCheck) {
        alert(minMaxQtyCheckMsg);
        return;
    }
    if (nUserPurchaseCountFlag) {
        alert(nUserPurchaseCountMsg);
        return;
    }

    cmAjax({
        url: '/order/cart/orderValidationCheck'
        , type: "post"
        , data: $('#frm').serialize()
        , dataType: "json"
        , success: function (data) {

            let isFail = false;
            let msg = '';
            let exhibitionCd = '';
            let exhibitionType = '';
            let newUserYn = 'N';
            if (!isEmptyExt(data.exhibitionList)) {
                $(data.exhibitionList).each(function (i, exhibition) {
                    // console.log(exhibition);
                    if (!exhibition.valid) {
                        isFail = true;
                        msg = exhibition.errorMsg;
                        exhibitionCd = exhibition.VExhibitionCd;
                        exhibitionType = exhibition.VExhibitionType;
                        newUserYn = exhibition.VNewUserYn;
                        // console.log(msg);
                        return;
                    }
                });
            }

            if (isFail) {

                if (exhibitionType == '03' && newUserYn == 'Y') {
                    if (confirm(msg)) {
                        location.href = '/promotion/exhibit/newMemList?exhibitionCd=' + exhibitionCd;
                    }
                } else {
                    alert(msg);
                }
                return;
            }

            isFail = false;
            if (!isEmptyExt(data.minPriceCheckList)) {
                $(data.minPriceCheckList).each(function (i, product) {
                    msg = product.VProductnm + '은(는) ' + SetNumComma(product.NMinPayPrice) + '원 이상 구매해야 합니다.';
                    isFail = true;
                    console.log(msg);
                    return;
                });
            }

            if (data.vNonmemberYn == 'N') {

                msg = '회원 휴대폰 번호가 정확하지 않습니다.\n휴대폰 번호 수정 후 주문하실 수 있습니다.';

                let cellNo = data.userInfo.v_cell_phoneno;

                if (isEmpty(cellNo)) {
                    alert(msg);
                    location.href = '/mypage/info/mypage';
                    return;
                }

                cellNo = cellNo.split('-').join('');

                const regExpPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;

                if (!regExpPhone.test(cellNo)) {
                    alert(msg);
                    location.href = '/mypage/info/mypage';
                    return;
                }

                msg = '회원 이메일 주소가 정확하지 않습니다.\n이메일 주소 수정 후 주문하실 수 있습니다.';

                let email = data.userInfo.v_email;
                if (isEmpty(email)) {
                    alert(msg);
                    location.href = '/mypage/info/mypage';
                    return;
                }

                const regExpEmail = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if (!regExpEmail.test(email)) {
                    alert(msg);
                    location.href = '/mypage/info/mypage';
                    return;
                }

            }

            if (!isEmptyExt(data.mustAddOptionList)) {

                $(data.mustAddOptionList).each(function (i, mustAddOption) {

                    if (mustAddOption.NAddOptionMustCount != mustAddOption.NAddOptionCount) {
                        isFail = true;
                        msg = '구매 수량이 제한된 옵션입니다.';
                    }

                })

            }

            if (!isEmptyExt(data.stockCheckList)) {

                $(data.stockCheckList).each(function (i, stockCheck) {

                    isFail = true;
                    msg = '구매하려는 수량보다 재고가 부족합니다. 수량을 다시 선택해주세요.\n' + stockCheck.VProductnm;
                    return false;

                })

            }

            if (isFail) {
                alert(msg);
                return;
            } else {
                // wnsgml351 인수인계 추가 - 비회원 주문여부 추가
                // 비회원 주문 여부
                const nonmemberYn = $('input[name="nonmemberYn"]').val();
                if (nonmemberYn == 'Y') {
                    $('#frm').attr('action', '/auth/login');
                }
                $('#frm').submit();
            }

        }
        , error: function (e) {
            console.log('e:' + JSON.stringify(e));
        }
    });
}

/**
 * 장바구니 등록 함수
 * @param cartType (00 바로구매, 01: 일반 장바구니)
 * @param arrayList (json형식의 데이터)
 * @param fnCallBack (callback 함수)
 * @param influNo (인플루언서 번호)
 */
function fnCartInsert(cartType, arrayList, fnCallBack, influNo) {

    //cartType 필수, arrayList 에 v_productcd 필수
    console.log('cartType:' + cartType + ' | arrayList:' + JSON.stringify(arrayList));
    /*
    2021-09-14
    var arr = [
        {"v_productcd":"3613","n_qty":2,"v_optionid":"20210914000000016376"}
        , {"v_productcd":"3613","n_qty":2,"v_optionid":"20210914000000016377"}
        , {"v_productcd":"3616","n_qty":2,"v_optionid":"20210914000000017947"}
        , {"v_productcd":"3616","n_qty":2,"v_optionid":"20210914000000017948"}
        , {"v_productcd":"3625","n_qty":2,"v_optionid":"20210914000000017956"}
        , {"v_productcd":"3625","n_qty":2,"v_optionid":"20210914000000017957"}
        , {"v_productcd":"3983","n_qty":2,"v_optionid":"20210914000000016498"}
        , {"v_productcd":"3983","n_qty":2,"v_optionid":"20210914000000016497"}
        , {"v_productcd":"4060","n_qty":2,"v_optionid":"20210914000000016552"}
        , {"v_productcd":"4060","n_qty":2,"v_optionid":"20210914000000016553"}
    ];
    fnCartInsert('01',arr,fnCartInsertCallBackTest,'');
     */
    cmAjax({
        url: '/order/cart/insertCartAjax'
        , type: "post"
        , data: {json: JSON.stringify(arrayList), cartType: cartType, influNo: influNo}
        , dataType: "json"
        , success: function (data) {
            //console.log("data:"+JSON.stringify(data));

            // [01:일반장바구니] 장바구니 개수 적용
            fnMainCartSetTxt(cartType);

            if (fnCallBack != undefined) {
                if(data.message == "noAddOptQty" || data.message == "wrongAddOptQty") {
                    fnCallBack(data.message);
                }else {
                    fnCallBack(data.status, cartType);
                }
            }
        }
        , error: function (e) {
            //console.log('e:'+JSON.stringify(e));
            if (fnCallBack != undefined) {
                fnCallBack("error");
            }
        }
    });

}

/**
 * 장바구니 [01:일반장바구니] 등록 시 장바구니 개수 적용
 */
function fnMainCartSetTxt(type) {

    if (type !== '01') {
        return false;
    }

    cmAjax({
        url: "/main/getUserCartCnt"
        , type: "post"
        , dataType: "json"
        , async: false
        , success: function (data) {
            const $count = $(".btn-util-cart .count");

            if ($count.length > 0) {
                $count.text(data.object);
            }
        }
        , error: function (e) {
        }
    });
}

/**
 * wnsgml351 인수인계 추가
 * 재입고알림 상품 신청
 */
function applyRestock(e) {
    e.preventDefault();

    const productCd = $(this).data('v-productcd');
    const optionId = $(this).data('v-optionid');
    //console.log("productcd : " + productCd);

    const loginId = '<c:out value="${_loginId}"/>';
    //console.log("loginId", loginId);
    if (!loginId) {
        location.href = "/auth/login";
        return false;
    }

    let formData = {
        "sitecd": $(this).data('v-sitecd'),
        "productcd": productCd
    };

    if (checkParamNullOrEmptyValue(optionId)) {
        formData['optionid'] = optionId;
    }

    cmAjax({
        url: "/product/prStockNotice"
        , type: "POST"
        , data: formData
        , dataType: "json"
        , success: function (data) {
            if (data.status == "succ") {
                $('#restock-popup').css('display', 'block');
            } else {
                alert(data.message);
                return false;
            }
        }
    });

}

/**
 * wnsgml351 인수인계 추가
 * 재입고알림 상품 팝업 닫기
 */
function closeRestockPop() {
    $('#restock-popup').hide();
}

function checkParamNullOrEmptyValue (parameter) {
    if (parameter === null || parameter === '' || parameter === undefined || parameter === 'null') return false;
    return true;
};

function loadRecommendProduct() {
    $('#recommendProdDiv').load('/order/cart/recommendProd');
}

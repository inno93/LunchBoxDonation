/**
 * 상품목록내 기능 구현 (PC) product.js
 */
$(document)
    .ready(function () { /* TODO */ })
    /**
     * 선물하기 옵션 또는 장바구니 옵션 팝업 호출
     */
    .on('click', 'li .btn-ext-gift, li .btn-ext-cart', function (e) {
        let me      = $(this),
            isCart  = me.hasClass('btn-ext-cart'),
            params  = $.extend({
                type: (isCart) ? 'CART' : 'GIFT'
            }, me.closest('li.ext-li').data());
        cmAjax({
            url: '/product/option/check',
            type: 'POST',
            dataType: 'json',
            data: params,
            scope: {
                target: me,
                params: params
            },
            success : function (data) {
                if (data.status === 'succ') {
                    let elem = document.createElement('div'),
                        that = $(elem),
                        params = this.scope.params;
                    that.attr({
                        'id': (params.type) ? 'popup-cart' : 'popup-gift',
                        'class': 'layer-wrap',
                        'style': 'display: none;'
                    }).load('/product/option/list', $.param(params), function (e) {
                        $('body').css('overflow', 'hidden');
                        $('.wrap').after(elem);
                        that.show();
                        // 한팩 담기 페이지일 경우
                        if($(".one-pack-wrap").length > 0){
                            $(".layer-pop").addClass("popup-cart-onePack");
                        }
                        // 한팩 담기 페이지일 경우 end
                    });
                }
                else {
                    alert(data.message);
                    // if (data.object.errorCode === 'CM102') {
                    //     let rUrl = window.location.pathname + window.location.search;
                    //     location.href = '/auth/login?loginRedirectUrl=' + gfnUrlEncoding(rUrl);
                    // }
                    // else
                    if (data.object.errorCode === 'CM103' && !data.object.isAdultCertify) {
                        let rUrl = window.location.pathname + window.location.search;
                        location.href = '/auth/adultCertify?redirectUrl=' + gfnUrlEncoding(rUrl);
                    }
                }
            },
            error: function (e) {
                alert(e.message);
            }
        });
    })
    /**
     * 찜하기 버튼 선택
     */
    .on('click', 'li .btn-ext-wish', function (e) {
        e.stopImmediatePropagation();
        const isLogin = commonCheckLogin();
        let me = $(this),
            isChecked = !(me.is(':checked'));
        if (!isLogin) {
            me.prop('checked', isChecked);
            return false;
        }
        let that = me.closest('li.ext-li'),
            data = that.data();
        if (isChecked) {
            gfnDelWishProduct(that, data);
        }
        else {
            gfnAddWishProduct(that, data);
        }
    })
    /**
     * 최근본상품
     */
    .on('click', 'li .btn-ext-remove', function (e) {
        e.stopImmediatePropagation();
        let me = $(this),
            vViewCd = me.closest('li.ext-li').data('vViewCd');
        confirms('상품을 삭제하시겠어요?', null, "okayCancel",bools);
        function bools(data){
            if (data){
                let param = me.closest('li.ext-li').data();
                deleteCookie('_click_prod', param.vProductcd.toString(), 1);
                location.reload();
            }
        }

    })
    /**
     * 비교하기 상품 선택 팝업 생성
     */
    .on('click', 'li .btn-ext-compare', function(e){
        e.stopImmediatePropagation();

        let me          = $(this),
            elem        = document.createElement('div'),
            that        = $(elem),
            param       = me.closest('li.ext-li').data();

        if(me.hasClass("not")){
            if($(".toast-pop").length === 0 ){
                // containerWrap.append('<div class="toast-pop"><div class="message"><p class="txt">상품이 품절되어 비교 담기가 불가능해요.</p></div></div>')
                confirms("상품이 품절되어\n비교 담기가 불가능해요.",null,"okay",null);

                // setTimeout(function () {
                //     closeToastPop ()
                // },1000)
            }
            return false;
        }
        if (!me.hasClass('on')) {

            // 비교담기 상품 갯수 체크
            if (!gfnValidCompareBasket()) { return; }

            if ((param.vOptionUseYn === 'Y' && param.vOptionShowYn === 'Y')) {
                that.attr({
                    'id'        : 'popup-compare',
                    'class'     : 'layer-wrap',
                    'tabindex'  : '0',
                    'style'     : 'display: none;'
                }).load('/product/compare/option', $.param($.extend({}, param)), function (e) {
                    that.data('target', me); // 호출한 버튼을 타겟으로 지정
                    $('body').css('overflow', 'hidden');
                    $('.wrap').after(elem);
                    that.show();
                });
            }
            else {
                if (!gfnCheckActiveCompareBasket()) {
                    // basket active
                    gfnGetCompareBasket().removeClass('hide');
                    compareScrollFix ();
                }
                gfnAddCompareBasket([{
                    vProductcd : param.vProductcd,
                    vCompareCd : param.vCompareCd,
                    vThumbnail : param.vThumbnail
                }]);
            }
        }
        else {
            gfnDelCompareBasket(me);
        }
    })
    .on('click', 'li .btn-ext-restock', gfnAddRestockProduct)
    .on('click', '#popup-compare-product .compare-btn', goComparePage)
    .on('click', '#popup-compare-product .compare-list .ui-compare-del', function (e) {
        gfnDelCompareBasket($(this));
    })
;
/**
 * 비교하기 미니 팝업 생성
 */
function gfnInitCompare(targetUrl, isMyPage, prePageUrl) {
    $('.content-wrap').after([
        '<div class="compare-layer-pop fixed hide" id="popup-compare-product">',
        '<form action="' + targetUrl + '" method="get">',
        '<input type="hidden" name="compareList" value="" />',
        '<input type="hidden" name="vType" value="4" />',
        '<input type="hidden" name="prePageUrl" value="' + prePageUrl + '" />',
        '</form>',
        '<div class="inner-box">',
        '<ul class="compare-list"></ul>',
        '<a href="#" class="btn-basic-lg2 btn-primary compare-btn w-full">',
        '<span>비교하기</span>',
        '</a>',
        '</div>',
        '</div>'
    ].join(''));

    if (isMyPage) {
        $('#popup-compare-product').addClass('type2');
    }
}

/**
 * 비교담기 미니 팝업 - element 가져오기
 * @returns {*|Window.jQuery|HTMLElement}
 */
function gfnGetCompareBasket() {
    return $('#popup-compare-product');
}

/**
 * 비교담기 미니 팝업 - 활성화 체크
 * @returns {boolean}
 */
function gfnCheckActiveCompareBasket() {
    return gfnGetCompareBasket().is(':visible');
}

/**
 * 비교담기 미니 팝업 - 상품목록 layer element 가져오기
 * @returns {*|Window.jQuery|HTMLElement}
 */
function gfnGetCompareBasketList() {
    return gfnGetCompareBasket().find('.compare-list');
}

/**
 * 비교담기 미니 팝업 - 검증
 * @returns {boolean}
 */
function gfnValidCompareBasket() {
    let popup = gfnGetCompareBasket();
    if (popup.length > 0) {
        if (popup.find('.compare-list li').length >= 3) {
            openToastPop(false)
            return false;
        }
    }
    return true;
}

/**
 * 비교담기 미니 팝업 - 상품 개수 가져오기
 * @returns {number}
 */
function gfnGetCompareBasketCnt() {
    let popup = gfnGetCompareBasket();
    return (popup.length === 0) ? 0 : popup.find('.compare-list li').length;
}

/**
 * 비교담기 미니 팝업 - 상품 담기
 */
function gfnAddCompareBasket(data) {
    let res = false;
    data.forEach(function (item, index, arys) {
        if (gfnGetCompareBasketList().find('li[data-v-compare-cd="{0}"]'.formatter(item.vCompareCd)).length === 0) {
            let li = document.createElement('li'),
                elem = ['<img src="{0}" alt="제품이미지">'.formatter(item.vThumbnail),
                    '<button type="button" class="btn-x-xs2 ui-compare-del">',
                    '<i class="ico-x-white"></i>',
                    '<span class="blind">삭제</span>',
                    '</button>'].join('');

            $(li).data(item).attr({
                'data-v-compare-cd': item.vCompareCd,
                'data-v-productcd': item.vProductcd
            }).append(elem);
            gfnGetCompareBasketList().append(li);
        }
        else {
            res = true;
        }
    });
    if (res) {
        alert('이미 선택된 상품입니다.');
    }
    gfnChangeCompareBtn();
    gfnActiveBasketBtn();
    openToastPop(gfnGetCompareBasketCnt());
}

/**
 * 비교담기 미니 팝업 - 상품 삭제
 */
function gfnDelCompareBasket(btn) {
    if (btn.hasClass('ui-compare-del')) {
        btn.closest('li').remove();
    }
    else {
        let productcd = btn.closest('li.ext-li').data('vProductcd');
        gfnGetCompareBasketList().find('li[data-v-productcd="{0}"]'.formatter(productcd)).remove();
    }
    gfnChangeCompareBtn();
    gfnActiveBasketBtn();
    if (gfnGetCompareBasketList().find('li').length === 0) {
        gfnGetCompareBasket().addClass('hide');
    }
}

/**
 * 비교담기 미니 팝업 - 비교담기 버튼 변경
 */
function gfnChangeCompareBtn() {
    let that = gfnGetCompareBasketList().find('li');
    $('li.ext-li button.btn-ext-compare.on').removeClass('on').children('span').text('비교 담기'); // 일단 전체 제거
    that.each(function (index, item) {
        let target = $('li.ext-li[data-v-productcd="{0}"]'.formatter($(item).data('vProductcd')));
        target.find('button.btn-ext-compare').addClass('on').children('span').text('비교 취소');
    });
}

/**
 * 비교담기 미니 팝업 - 비교담기 버튼 활성화
 */
function gfnActiveBasketBtn() {
    if (gfnGetCompareBasketList().find('li').length >= 2) {
        gfnGetCompareBasket().find('.btn-primary').removeClass('btn-dim');
    }
    else {
        gfnGetCompareBasket().find('.btn-primary').addClass('btn-dim');
    }
}

/**
 * 비교담기 미니 팝업 - 비교페이지로 이동
 */
function goComparePage() {
    if (!$(this).hasClass('btn-dim')) {
        let form = gfnGetCompareBasket().find('form'),
            input = form.find('[name="compareList"]');

        let compareList = [];
        gfnGetCompareBasketList().find('li').each(function (index, item) {
            compareList.push($(item).data());
        });
        input.val(JSON.stringify(compareList));
        form.submit();
    }
}

/**
 * Toast alert
 * @param cnt
 */
function openToastPop(cnt) {
    if(cnt == false){
        var toastAry = [
            '<div class="toast-pop">',
            '<div class="message">',
            '<p class="txt">최대 3개의 상품까지 비교가 가능해요.<br> 상품을 삭제 후 다시 담아주세요.</p>',
            //'<p class="sub-txt">(3개까지 비교가능)</p>',
            '</div>',
            '</div>'
        ];
    }else{
        var toastAry = [
            '<div class="toast-pop">',
            '<div class="message">',
            '<p class="txt">{0}개의 비교 상품이 담겼어요.</p>'.formatter(cnt),
            //'<p class="sub-txt">(3개까지 비교가능)</p>',
            '</div>',
            '</div>'
        ];
    };
    if($('.compare-layer-pop').length > 0) {
        $('.compare-layer-pop').append(toastAry.join(''));
    }else{
        $('.container').append(toastAry.join(''));
    }

    setTimeout(function () {
        closeToastPop();
    },1000);
}

/**
 * 찜한 상품 등록
 * @param data : Object
 */
function gfnAddWishProduct(target, data) {
    if (isEmptyExt(data)) {
        return;
    }

    let param = {
        productCd : data.vProductcd
    };

    cmAjax({
        url     : '/product/prAddDib',
        type    : 'post',
        dataType: 'json',
        data    : param,
        success : function (data) {
            confirms("상품이 찜한 상품에 \n등록 되었어요.",null,"okay",null);
            if (data.status == "succ") {
                $(target).data('vWishid', data.object.vWishId);
            }
        },
        error   : function (e) {
            alert(e.message);
        }
    });
}

/**
 * 찜한 상품 삭제
 * @param target : jQueryElement
 * @param data : Object
 */
function gfnDelWishProduct(target, data) {

    let param = {};

    if (!isEmptyExt(data) && !isEmptyExt(data.vWishid)) {
        param.wishid = data.vWishid;
    }

    cmAjax({
        url     : '/product/prDelDib',
        type    : 'post',
        dataType: 'json',
        data    : param,
        context : target,
        success : function (data) {
            let $this = this;
            confirms("상품이 찜한 상품에서 \n삭제 되었어요",'','okay',bools);
            function bools(callback){
                if(callback){
                    if (data.status === 'succ') {
                        let me = $this.context;
                        if ($(me).data('vViewCd') === 'PRD150') {
                            window.location.reload();
                        }
                        console.log(this.context);
                    }
                }
            }
        },
        error   : function (e) {
            alert(data.message);
        }
    });
}

/**
 * 재입고알림 등록
 * @param Object
 */
function gfnAddRestockProduct(e) {
    e.stopImmediatePropagation();
    let me = $(this),
        li = me.closest('li.ext-li'),
        data = li.data();
    cmAjax({
        url: '/product/prStockNotice',
        type: 'POST',
        dataType: 'json',
        data: {
            productcd: data.vProductcd,
            optionid: data.vProductcd
        },
        success: function (data) {
            alert(data.message);
            if (data.status === 'fail') {
                let rUrl = window.location.pathname + window.location.search;
                location.href = '/auth/login?loginRedirectUrl=' + gfnUrlEncoding(rUrl);
            }
        },
        error: function (data) {
            alert(data.message);
        }
    });
}

/**
 * 쿠키 삭제
 * @param cName
 * @param productCd
 * @param expireDay
 */
function deleteCookie(cName, productCd, expireDay) {

    let items = getCookie(cName);

    if (!items) {
        return;
    }

    let itemArray = items.split(','),
        itemIndex = itemArray.indexOf(productCd);

    if (itemIndex != -1) {
        itemArray.splice(itemIndex, 1);
    }

    items = itemArray.join(',');
    setCookie(cName, items, expireDay);
}

/**
 * 쿠키값 가져오기
 * @param cookie_name
 * @returns {string}
 */
function getCookie(cookie_name) {
    var x, y;
    var val = document.cookie.split(';');

    for (var i = 0; i < val.length; i++) {
        x = val[i].substr(0, val[i].indexOf('='));
        y = val[i].substr(val[i].indexOf('=') + 1);
        x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
        if (x == cookie_name) {
            return unescape(y); // unescape로 디코딩 후 값 리턴
        }
    }
}

/**
 * 상품목록 $.load / $.post / $.get 이후 callback
 * @param elem
 */
function fnLoadCallback(elem) {
    fnLozad();
    if (isEmptyExt(elem) || typeof elem === 'string') {
        return 0;
    }

    return elem.find('input[name="gvResultTotalCount"]').remove().val(); // 전체카운트 html 삭제시 리턴
}

/**
 * 이미지 lozad 실행
 */
function fnLozad() {
    // lazy loads elements with default selector as '.lozad'
    let observer = lozad();
    observer.observe();
}
/******************************************************************************
 * 타임세일
 * @param interval
 ******************************************************************************/
function gfnSetTimesale(interval, target, view) {


    if(!String.prototype.padStart) {
        String.prototype.padStart = function padStart(targetLength, padString) {
            if(this.length >= targetLength) {
                return String(this);
            } else {
                if(padString == null || padString == " ") {
                    padString = " ";
                } else if(padString.length > 1) {
                    padString = padString.substring(0,1);
                }
                targetLength = targetLength - this.length;
                var prefix = "";
                for(var i = 0; i < targetLength; i++) {
                    prefix += padString;
                }
                return prefix + String(this);
            }
        };
    }



    target.each(function (index, item, arys) {
        let me    = $(item),
            data  = me.data(),
            open  = Number(data.openSec) - interval,
            sec   = Number(data.sec) - interval,
            day   = 0, hour = 0, min = 0,
            hourStr = '', minStr = '', secStr = '';

        if (sec > 0) {
            let openLabel = me.closest('li.ext-li').find('.prd-label.open');
            if (open > 0) {
                sec = open;
            }

            if (open <= 0 && openLabel.length > 0) {
                openLabel.remove();
            }

            day  = parseInt(sec / 60 / 60 / 24);
            sec  = (sec - (day * 60 * 60 * 24));
            hour = parseInt(sec / 60 / 60);
            hourStr = String(hour).padStart(2,'0');
            sec  = (sec - (hour * 60 * 60));
            min  = parseInt(sec / 60);
            minStr = String(min).padStart(2,'0');
            sec  = (sec - (min * 60));
            secStr = String(sec).padStart(2,'0');

            if (view === 'main') {

                me.find('.day').removeClass('hide').text(day);
                me.find('.hour').removeClass('hide').text(hourStr);
                me.find('.min').removeClass('hide').text(minStr);
                me.find('.sec').removeClass('hide').text(secStr);
                me.find('.txt').removeClass('hide');
            } else {

                me.find('.t-day').removeClass('hide').text(day + '일');
                me.find('.t-hour').removeClass('hide').text(hour + '시간');
                me.find('.t-minute').removeClass('hide').text(min + '분');
                me.find('.t-second').removeClass('hide').text(sec + '초');
            }

        }
        else {


            if (view === 'main') {

                if (!me.find('.day').hasClass('hide')) { me.find('.day').addClass('hide'); }
                if (!me.find('.hour').hasClass('hide')) { me.find('.hour').addClass('hide'); }
                if (!me.find('.min').hasClass('hide')) { me.find('.min').addClass('hide'); }
                if (!me.find('.sec').hasClass('hide')) { me.find('.sec').addClass('hide'); }
                if (!me.find('.txt').hasClass('hide')) { me.find('.txt').addClass('hide'); }

                me.find('.t-expiry').removeClass('hide');
            } else {

                if (!me.find('.t-day').hasClass('hide')) { me.find('.t-day').addClass('hide'); }
                if (!me.find('.t-hour').hasClass('hide')) { me.find('.t-hour').addClass('hide'); }
                if (!me.find('.t-minute').hasClass('hide')) { me.find('.t-minute').addClass('hide'); }
                if (!me.find('.t-second').hasClass('hide')) { me.find('.t-second').addClass('hide'); }
                if (!me.find('.t-day').hasClass('hide')) { me.find('.t-day').addClass('hide'); }
                me.find('.t-expiry').removeClass('hide');
            }
        }
    });
}

/**
 * https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/String/padStart
 * IE 11에서 padStart() 메소드를 지원하지 않아서 적용되는 소스
 */
if (!String.prototype.padStart) {
    String.prototype.padStart = function padStart(targetLength,padString) {
        targetLength = targetLength>>0; //truncate if number or convert non-number to 0;
        padString = String((typeof padString !== 'undefined' ? padString : ' '));
        if (this.length > targetLength) {
            return String(this);
        }
        else {
            targetLength = targetLength-this.length;
            if (targetLength > padString.length) {
                padString += padString.repeat(targetLength/padString.length); //append to original to ensure we are longer than needed
            }
            return padString.slice(0,targetLength) + String(this);
        }
    };
}

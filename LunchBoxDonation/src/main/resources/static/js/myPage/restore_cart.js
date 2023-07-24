
function restoreUserCart(e) {
    e.preventDefault();

    var $this = $(this);
    var cartId = $this.data("cartid");

    if (cartId === "") {
        return;
    }

    var gProductcd = $this.data("gprodid");
    var productcd = $this.data("prodid");
    var optionid = $this.data("optionid");
    var onepackYn = $this.data("onepackyn");
    var qty = $this.data("qty");
    var textOption1 = $this.data("textoption1");
    var textOption2 = $this.data("textoption2");
    var textOption3 = $this.data("textoption3");

    var cartArray = [];
    var cart = {
        "v_productcd" : productcd,
        "v_gproductcd" : gProductcd,
        "v_optionid" : optionid,
        "v_uproductcd" : gProductcd,
        "v_add_prod_yn": 'N',
        "v_text_option1" : textOption1,
        "v_text_option2" : textOption2,
        "v_text_option3" : textOption3,
        "n_qty" : qty,
        "v_onepack_yn" : onepackYn,
        "v_exhibition_cd": "",
        "v_timesale_cd" : "",
    };
    // TODO 기획전, 타임세일, 묶음상품코드 체크 확인
    cartArray.push(cart);

    cmAjax({
        url: "/order/cart/restore",
        type: "post",
        async: false,
        data: {
            "vCartid" : cartId,
        },
        success: function (res) {
            if (res.status === "succ") {
                try {
                    confirms('상품이 추가되었습니다.\n장바구니로 이동하시겠습니까?',null,'okayCancel',callbackValue);
                    function callbackValue(bool){
                        if (bool) {
                            location.href = "/order/cart";
                        }
                    }
                } catch (e) {
                    if (confirm("장바구니에 추가하였습니다.\n장바구니로 이동하시겠습니까?")) {
                        location.href = "/order/cart";
                    }
                }
            } else {
                alert("장바구니 등록 실패하였습니다.");
            }
        }
    });

    // fnCartInsert('01', cartArray, function(status, cartType) {
    //
    //     if(status == 'succ'){
    //         if (confirm("장바구니에 추가하였습니다.\n장바구니로 이동하시겠습니까?")) {
    //             location.href = "/order/cart";
    //         }
    //         reloadCartCount();
    //     } else {
    //         alert("장바구니 등록 실패");
    //     }
    // }, '');

}

function reloadCartCount() {

    console.log("setCartCnt in");

    cmAjax({
        url       : "/main/getUserCartCnt"
        , type      : "post"
        , dataType  : "json"
        , async     : false
        , success   : function ( data ) {
            console.log("data", data);
            let cartCnt = data.object;
            $('.my-menu li').find('.count').text(cartCnt);
        }
        , error: function ( e ) {}
    });
}


function restoreCartAll(ordercd) {

    var $this = $(this);

    if (ordercd === "") {
        return;
    }

    cmAjax({
        url: "/order/cart/restoreOrderCart",
        type: "post",
        async: false,
        data: {
            "vOrdercd" : ordercd,
        },
        success: function (res) {
            if (res.status === "succ") {
                return ;
            } else {
                alert("장바구니 등록 실패하였습니다.");
            }
        }
    });

}

$(document).ready()
    .on("click", ".btn-restore-cart", restoreUserCart)
// .on("click", "btn-restore-cart-all", restoreCartAll)
;

/*
    ※ 적용화면
    [추가] 정기배송관리 > (탭) 주문내역,
    [추가] 정기배송관리 > (탭) 주문내역 > 정기배송 상세내역
    [추가] (팝업) 정기배송일자변경
    [추가] 정기배송관리 > (탭) 결제카드 관리,
    [추가] 정기배송 주문 > 결제화면
    [추가] 상품상세(정기배송 신청)
*/




/* content_ui.js ============================== */

$(function(){
    // 카드등록 목록
    if ($('.card-box-list').length > 0) { cardSwiperSlide (); }
});



/****** 카드등록 목록 ******/
//var cardSwiper = null;

function cardSwiperSlide () {

    // if(cardSwiper != null) cardSwiper.destroy();

    var cardSwiper = new Swiper('.card-box-list', {
        //  spaceBetween: 25,
        spaceBetween: 15, //20
        slidesPerGroup :1,
        slidesPerView: 'auto',
        centeredSlides: true,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        touchRatio: true,
        clickable: true,
        slideToClickedSlide:true,
        // loop: true,
    });

    // 마이페이지 > 정기배송관리 > 결제카드, 결제화면 : 스와이프 제거
    if($('.payment-detail').hasClass('type-view')) cardSwiper.destroy();

    // 결제화면 : 스와이프 제거
    if($('.payment-detail').hasClass('active')) cardSwiper.destroy();
    //cardSwiper.init();
}




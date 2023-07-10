$(function () {
    // Gnb
    if ($('.gnb-wrap').length > 0) { gnb(); }

    // Top Banner Close
    if ($('.top-banner').length > 0) { topBnrClose(); }

    // Side banner
    if ($('.side-quick').length > 0) { sideQuickBnrPos(); }

    // header-search
    if ($('.header-search').length > 0) { headerSearch(); }

    // Tab
    if ($('.ui-tab').length > 0) { tabMenu();}

    // Custom Select box
    if ($('.ui-select').length > 0) { selectBox(); }

    // Custom Select box
    if ($('.dropdown-box').length > 0) { dropDown(); }

    // Layer popup
    if ($('.ui-open-pop').length > 0) { layerPop(); }

    // Layer popup Height
    // if ($('.layer-wrap').is(':visible')) { testlayerHeight(); } // 팝업html에서 동작 ()

    // tooltip
    if ($('.ui-tooltip').length > 0) { toolTip(); }

    if ($('.hover-tooltip').length > 0) { hoverToolTip(); }

    // 체크박스 모두 선택
    if ($('.ui-check-all').length > 0) { checkAll();}

    // Toggle
    if ($('.ui-toggle').length > 0) { toggleCnt();}

    if ($('.ui-toggle-height').length > 0) { toggleHeight();}

    if ($('.ui-toggle-slide').length > 0) { toggleSlide();}

    // 주문결제 박스 스크롤 고정
    if ($('.ui-box-fix').length > 0) { paymentFix();}

    // 아코디언
    if ($('.ui-accordion').length > 0) { accordionList();}

    // 목록, 내용 더보기
    if ($('.ui-more-list').length > 0) { moreListView(); }

    // 파일첨부
    if ($('.filebox').length > 0) { fileAdd(); }

    // 식단상담 슬라이드
    if ($('.meal-plan-list').length > 0) { planSwiperSlide(); }

    // 상품상세 옵션선택 고정
    if ($('.option-select-fix').length > 0) { optionFix(); }

    // 상품상세 탭, 옵션선택박스
    if ($('.ui-goods-tab').length > 0) { goodsTab(); }

    // 상품상세 탭, 옵션선택박스
    if ($('.ui-more-article').length > 0) { moreArticle(); }

    // 브랜드관 슬라이드
    if ($('.brand-swiper-list').length > 0) { brandSwiperSlide(); }

    if ($('.ui-togglelayer-btn').length > 0) { toggleLayer(); }

    // 브랜드찾기
    if ($('.ui-brand-search').length > 0) { brandSearch(); }

    // review 상세 슬라이드
    if ($('.review-slide').length > 0) { reviewSwiperSlide(); } // .review-detail-slide

    // 랭킹 탭 슬라이드
    if ($('.ranking-swiper-tab').length > 0) { rankTabSwiperSlide(); }
    if ($('.ranking-swiper-thriftyTab').length > 0) { rankThriftyTabSwiperSlide(); }
    if ($('.ranking-swiper-stab').length > 0) { rankStabSwiperSlide(); }

    //선물하기 카테고리
    if ($('.gift-content .cate-swiper-tab').length > 0) { cateSwiperSlide(); }
    if ($('.gift-sub-content .cate-swiper-tab').length > 0) { cateSwiperSlide2(); }

    // 주문목록(마이페이지)
    if ($('.order-swiper-list').length > 0) { orderSwiperSlide(); }

    // 카드등록 목록
    if ($('.card-box-list').length > 0) { cardSwiperSlide (); }

    // 마이페이지 주문상품 검색 팝업 스크롤목록 높이 조절
    if ($('.ui-height-auto').length > 0) { heightAuto(); }

    // 한팩담기 bestPrdSwiperSlide
    if ($('.best-prd-list').length > 0) { bestPrdSwiperSlide(); }

    // 이달의 특가 탭
    if ($('.ui-anchor-tab').length > 0) { anchorMove (); }

    if ($('.scroll-select-bar').length > 0) { selectBarFix (); }

    // 상품카테고리 탭
    if ($('.scroll-fix-tab').length > 0) { scrollFixTab (); }

    // 상품목록 이벤트 배너
    if ($('.prd-event-slide').length > 0) { eventSwiperSlide (); }

    // 상품목록 상세 검색
    if ($('.prd-search-box').length > 0) { prdDetailSearch (); }

    // 쿠폰 사용정보 클릭시 레이어
    // if ($('.ui-coupon-info').length > 0) { couponInfoLayer (); }

    // mCustomscrollbar
    if ($('.ui-custom-scroll').length > 0) { customScroll ();}

    // 이달의 쿠폰 : 등급별 쿠폰
    if ($('.coupon-level-list').length > 0) { couponLevelSlide(); }

    if ($('.ui-coupon-info').length > 0) { openCouponInfo(); }

    if ($('.coupon-wrap-box').length > 0) { moreCouponView(); }

    //선물하기 swiper
    if($('.visual-slide').length>0){visualSlide();}
    //배너 swiper
    if($('.banner-default-swipe').length>0){bannerDefaultSwiper();}

    //메시지
    if($('.message-swiper').length>0){messageSlide();}

    //side event
    if($('.side-event').length>0){sideEvent();}

    //이미지보기 팝업 스와이프
    if ($('.img-auto-slide').length > 0) { imgAutoSlide(); }

    //상품상세 증정품 스와이프
    if ($('.default-swipe').length > 0) { defaultSlide(); }

    //기본 탭
    if ($('.ui-tab').length > 0) { defaultTab(); }

    //상품상세
    if ($('.goods-slide').length > 0) { goods_Slide(); }

    //검색결과 구분
    if ($('.sorting-result').length > 0) { sortingToggle(); }

    //특급배송 lnb fixed
    if ($('.exp-content').length > 0) { expFixed(); }

    //별별 랭킹
    if ($('.star-rank-swiper').length > 0) { starRankSlide(); }
});

/****** gnb, Category ******/
function gnb () {
    var btnAll = $('.btn-menu-all'),
        cateWrap = $('.all-depth-cate'),
        cateDep1 = $('.all-depth-cate .cate-dep1 li a');

    btnAll.on('mouseenter',function () {
        categoryOpen ();

    })

    cateWrap.on('mouseleave',function () {
        categoryClose ();
    })

    cateDep1.on('mouseenter',function () {
        cateDepth1(this);
    })

    // scroll
    /* var gnb = $('.gnb-wrap'),
        gnbPos = gnb.offset().top;

    $(window).on('load scroll', function(){
        var docScroll = $(document).scrollTop();
         // 스크롤시 gnb header 고정
        if( docScroll > gnbPos ){
            gnb.addClass('fixed')
        } else {
            gnb.removeClass('fixed')
        }
    });     */
}
// GNB scroll
var lastScrollTop = 0;
function gnbScroll(){
    var winS = $(window).scrollTop();
    var gnbw = $('.gnb-wrap'),
        gnbPosition = gnbw.position(),
        allCate = $(".all-depth-cate");
    var $header= $('.header'),
        $headerP = $header.offset().top,
        $headerH = $header.height();

    if(winS > lastScrollTop) { //downscroll

        if( winS > gnbPosition.top){
            gnbw.addClass('fixed')
            allCate.addClass('fixed')
        } else {
            gnbw.removeClass('fixed')
            allCate.removeClass('fixed')
        }
    } else { //upscroll

        if( winS < $headerP + $headerH){
            gnbw.removeClass('fixed')
            allCate.removeClass('fixed')
        } else{
            gnbw.addClass('fixed')
            allCate.addClass('fixed')
        }
    }
    lastScrollTop = winS;
}
$(window).on('load scroll', function() {
    if ($('.gnb-wrap').length > 0) { gnbScroll();}
});

function categoryOpen () {
    $(".all-depth-cate").css({"display": "block",});
    if($(".swiper-con .swiper-notification").length == 0){
        var swiper = new Swiper('.swiper-con', {
            slidesPerView: 'auto',
            spaceBetween: 2, // 슬라이드 간격 조절
            notification:false,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });
    }
}

function categoryClose () {
    $(".all-depth-cate").css("display",'none');
}
function cateDepth1(el){
    $(el).parent("li").siblings().removeClass("on");
    $(el).parent("li").addClass("on");
    let hoverVal = $(el).attr("data-cd1");
    $(".all-depth-cate .depth2").css("display","block")
    $(".all-depth-cate .cate-dep2").each(function(index,el){
        var item = $(el).attr("data-cd1");
        if(hoverVal === item){
            $(el).css("display","block");
        }else{
            $(el).css("display","none");
        }
    })
}



/****** Banner ******/
// Top Banner Close
function topBnrClose () {
    var topBnr = $('.top-banner'),
        closeBtn = topBnr.find(".btn-x-md2");

    closeBtn.on('click',function () {
        topBnr.hide();
    })
}

// Side Banner
function sideQuickBnrPos () {
    wrap = $('.wrap');
    sideQuickArea = $('.side-quick-area');
    sideQuickBnr = sideQuickArea.children('.side-quick');

    cntPos = $('.container').find('.content-wrap');

    if ( wrap.hasClass('main') ) {
        mainPos = $('.container').find('.main-article').eq(0);
        mainPosTop = mainPos.position().top + 175;

        //sideQuickBnr.css('top', mainPosTop);
    }
    // else {

    //     if ( cntPos.children('.scroll-select-bar').length > 0 ) {
    //         cntPosTop = cntPos.position().top + 125;
    //     } else {
    //         cntPosTop = cntPos.position().top + 70;
    //     }
    //     //sideQuickBnr.css('top', cntPosTop);
    // }

    $(window).on('load scroll', function(){
        docScroll = $(document).scrollTop();
        contentHeight = $('.container').height(),
            footHeight = $(".footer").outerHeight(), // +275
            sideQuickHeight = $('.side-quick').height();

        if ( wrap.hasClass('main') ) {
            if( docScroll > mainPos.offset().top ){
                sideQuickScroll ();
            } else {
                sideQuickArea.removeClass('fixed')
                //sideQuickBnr.css('top', mainPosTop);
            }
        } else if ( cntPos.hasClass('benefit-content') ) {
            if( docScroll > cntPos.find('.benefit-template').eq(0).position().top + 120 ){
                sideQuickScroll ();
            } else {
                sideQuickArea.removeClass('fixed')
                //sideQuickBnr.css('top', mainPosTop);
            }
        } else {
            if( docScroll > cntPos.offset().top ){
                sideQuickScroll ();
            } else {
                sideQuickArea.removeClass('fixed')
                //sideQuickBnr.css('top', cntPosTop);
            }
        }
    });

    function sideQuickScroll () {
        //cntPos = $('.container').find('.content-wrap');

        if(contentHeight > 840) {
            if ( cntPos.children('.scroll-select-bar').length > 0 ) {
                sideQuickArea.addClass('fixed type2');
            } else {
                sideQuickArea.addClass('fixed');
            }
            sideQuickBnr.removeAttr('style');

            if ( docScroll >= contentHeight - footHeight - 323) { // 255
                sideQuickArea.addClass('static').find('.side-quick').css('top',-sideQuickHeight)
            } else {
                sideQuickArea.removeClass('static')
            }
        } else {
            sideQuickArea.removeClass('fixed').addClass('static');
            sideQuickBnr.removeAttr('style');
        }
        if( $('.container').find('.no-data.search-type') && contentHeight <= 800){
            if ( docScroll >= contentHeight - footHeight ) {
                sideQuickArea.addClass('static').find('.side-quick').css('top',10)
            } else {
                sideQuickArea.removeClass('static')
            }
        }
    }
}




/****** 화면 상,하단 이동 ******/
function goTop () {
    // $('html, body').scrollTop(0);
    $('html, body').stop().animate({scrollTop : '0'})
}

function goBottom () {
    // $('html, body').scrollTop( $(document).height() );
    $('html, body').stop().animate({scrollTop : $(document).height()})
}


/****** header Search ******/
function headerSearch () {
    var targetInput = $('.input-search');
    var targetWrapper = $('.header-inner .header-search');
    layer = $('.search-layer');
    targetInput.on('click',function () {
        $('.top-search').addClass('on')
        layer.addClass('on');
        targetWrapper.addClass('on');
        $('body').on('click',function(e){
            if (   $(e.target).closest('.header-search').length === 0
                && layer.hasClass('on')
                && !$(e.target).hasClass('del-body')) {
                $('.top-search').removeClass('on');
                layer.removeClass('on');
                targetWrapper.removeClass('on');
            }
        })
    })
}


/****** Tab ******/
function tabMenu () {
    var tab = $('.ui-tab'),
        anchor = tab.find('li').children('a');

    anchor.on('click', function () {
        var tabID = $(this).attr("href");
        tabClick (this);
        if ($(this).parents().siblings('.ui-tab-container').length > 0) {
            $(tabID).addClass("active").siblings().removeClass("active");
        }
    });
}

function tabClick (el) {
    $(el).parent('li').addClass('current').siblings('li').removeClass('current');
}


/****** dropDown ******/
function dropDown (e) {
    $(document).on('click','.dropdown-value',function(e){
        if ( !$(this).hasClass('disabled') ) {
            if ( $(this).parent('.dropdown-box').hasClass('on')) {
                // dropDownClose ();
                $(this).parent('.dropdown-box').removeClass('on')
            } else {
                //dropDownClose ();
                $(this).parent().siblings('.dropdown-box').removeClass('on');
                $(this).parent('.dropdown-box').addClass('on');
            }

            $('body').on('click',function(e){
                if($(e.target).closest('.dropdown-box').length === 0 && $('.dropdown-box').hasClass('on')){
                    dropDownClose ()
                }
            })
        }
    });

    $(document).on('click','.dropdown-list a',function(){
        dropDownAction(this);
    })
}

function dropDownAction (el) {
    $(el).parents('.dropdown-list').find('li').not('.disabled').children('a').not('.text-primary').removeClass('selected');

    if ( !$(el).parent('li').hasClass('disabled') ) {
        $(el).not('.text-primary').addClass('selected');
    }
    // dropDownClose ();
    $(el).parents('.dropdown-box').removeClass('on')
}

function dropDownClose () {
    $('.dropdown-box').removeClass('on');
    // return false;
}


/****** SelectBox ******/
function selectBox (e) {
    $(document).on('click','.select-value',function(e){
        if ( !$(this).hasClass('disabled') ) {
            if ( $(this).parent('.ui-select').hasClass('on')) {
                selectBoxClose ();
            } else {
                selectBoxClose ();
                $(this).parent('.ui-select').addClass('on');
            }

            $('body').on('click',function(e){
                if($(e.target).closest('.ui-select').length === 0 && $('.ui-select').hasClass('on')){
                    selectBoxClose ()
                }
            })
        }
    });

    $(document).on('click','.select-list a',function(){
        selectBoxAction(this);
    })
}

function selectBoxAction (el) {
    var listValue = $(el).children('span').text();

    $(el).parents('.select-list').find('ul li a').removeClass('selected');
    $(el).addClass('selected');
    $(el).parents('.ui-select').find('.select-value span').text(listValue);

    var selectedValue = $(el).parent('li').data('name');
    $(el).parents('.ui-select').data('value',selectedValue);

    if ($(el).hasClass('selected') ) {
        if ( !$(el).parent('li').hasClass('first') ) {
            $(el).parents('.select-list').prev('.select-value').removeClass('placeholder');
        } else if ( $(el).parent('li').hasClass('first') ) {
            $(el).parents('.select-list').prev('.select-value').addClass('placeholder');
            $(el).parents('.ui-select').attr('data-value','');
        }
    }

    selectBoxClose ();
}

function selectBoxClose () {
    $('.ui-select').removeClass('on');
    // return false;
}


/****** LayerPop ******/
function layerPop (id) {
    var btnAnchor = $('.ui-open-pop'),
        close = $('.ui-close-pop')

    btnAnchor.on('click', function () {
        btnTarget = $(this);
        var popupID;

        if ( btnTarget.is('button') ) {
            popupID = btnTarget.attr('data-layer');
        } else if ( btnTarget.is('a') ) {
            popupID = btnTarget.attr("href")
        }

        popLayer = $(popupID);

        if ( !btnTarget.parents('.layer-pop').length ) {
            layerOpen (popLayer);
        } else if ( btnTarget.parents('.layer-pop').length ) {
            innerlayerOpen (popLayer);
        }
    })

    var popupElem = $(id);
    if ( $(id).hasClass('onload') ) {
        layerOpen (popupElem);
    }

    close.on('click', function () {
        btnTarget = $(this);
        if ( !popupElem.hasClass('onload') ) {
            layerClose($(this), btnTarget)
        } else {
            layerClose($(this))
        }
    })
}

function layerOpen (id) {
    var scrollValue = $('body').scrollTop();

    $('body').css({'overflow':'hidden'}).scrollTop(scrollValue);
    $(id).show().attr('tabIndex','0').focus;

    //layerHeight ( $(id) );
}

function innerlayerOpen (id) {
    var scrollValue = $('body').scrollTop();

    $('body').addClass('layer-dim').scrollTop(scrollValue);
    $(id).show().addClass('layer-index').attr('tabIndex','0').focus;

    layerHeight ()
}

function layerClose (el, btnTarget) {
    var layerWrap = $(el).parents('.layer-wrap');

    if ( !$(el).parents('.layer-index').length ) {
        $('body').removeAttr('style');
    } else if ( $(el).parents('.layer-index').length ) {
        $('body').removeClass('layer-dim');
    }

    $('.layer-pop:not(.main-view-popup)').removeAttr('style');
    layerWrap.hide();

    if ( !layerWrap.hasClass('onload')) {
        btnTarget.focus();
    }
}

function testlayerHeight () {
    var layer = $('.layer-pop'),
        layerHeight = layer.outerHeight();
    mathHeight = Math.floor(layerHeight)

    if ( mathHeight % 2 == 0 ) {
        // console.log('짝수')
        //  console.log(layerHeight)
    } else {
        //  console.log(layerHeight)
        layer.css('height',mathHeight + 1);
    }
}

function layerHeight (el) {
    var layer = $(el),
        // layerHeight = layer.find('.layer-pop').height();
        mathHeight = Math.floor(layerHeight)

    if ( mathHeight % 2 == 0 ) {
        //  console.log('짝수')
    } else  {
        layer.css('height',mathHeight + 1);
    }
}

/****** tooltip ******/
function hoverToolTip (){
    var hoverBtn = $('.hover-tooltip');

    hoverBtn.on('click mouseenter',function () {
        var btnPos = ( $(this).position().left - $(this).width() ) / 2,
            hoverTipBox = $(this).next('.dlv-tipbox-bottom.type3');
        //hoverTipBox = $(this).next('[class*="dlv-tipbox-"]');
        hoverTipBox.addClass('on').css('margin-left',Math.ceil(btnPos) + 2)

        //console.log($(this).position().left)
    });

    hoverBtn.on('mouseleave',function () {
        // hoverTipBox = $(this).next('[class*="dlv-tipbox-"]');
        hoverTipBox = $(this).next('.dlv-tipbox-bottom.type3');
        hoverTipBox.removeClass('on').removeAttr('style')
    });
}



function toolTip () {
    btnTip = $('.ui-tooltip'),
        btnClose = $('.ui-tooltip-close'),
        tipbox = $('[class*="ui-tooltipbox-"]');

    if ( btnTip.hasClass('ui-hover') ) {
        btnTip.on('click mouseenter',function () {
            tipShowEvent (this)
        });
        btnTip.on('mouseleave',function () {
            tooltipClose(targetTipBox)
        });
    } else {
        btnTip.on('click',function () {
            tipShowEvent (this)
        });
        btnClose.on('click',function () {
            tooltipClose(targetTipBox)
        });
    }

    function tipShowEvent (el) {
        var offTop = $(el).position().top - $(el).outerHeight() - 15, //
            offLeft = $(el).position().left - $(el).outerWidth(),
            offRight = $(el).position().left + $(el).outerWidth() + 15, //
            offBottom = $(el).position().top + $(el).outerHeight() + 10,
            offCenter = $(el).position().left;

        targetTipBox = $(el).next(tipbox);

        if ( targetTipBox.hasClass('on') ) {
            targetTipBox.removeClass('on');
            tooltipClose(targetTipBox);
        } else if ( !$(el).hasClass('on') ) {
            targetTipBox.addClass('on');

            if ( targetTipBox.hasClass('ui-tooltipbox-top') ) {
                tooltipTopOpen ( targetTipBox, offTop, offCenter)
            } else if ( targetTipBox.hasClass('ui-tooltipbox-bottom') ) {
                tooltipBottomOpen ( targetTipBox, offBottom, offCenter)
            } else if ( targetTipBox.hasClass('ui-tooltipbox-left') ) {
                tooltipLeftOpen ( targetTipBox, offTop, offLeft)
            } else if ( targetTipBox.hasClass('ui-tooltipbox-right') ) {
                tooltipRightOpen ( targetTipBox, offTop, offRight)
            }
        }
    }
}

function tooltipTopOpen ( targetTipBox, top, center ) {
    targetTipBox.show().css({'top':top+'px', 'left':center+'px'}).focus();
}

function tooltipBottomOpen ( targetTipBox, top, center ) {
    targetTipBox.show().css({'top':top+'px', 'left':center+'px'}).focus();
}

function tooltipLeftOpen ( targetTipBox, top, left ) {
    targetTipBox.show().css({'top':top+'px', 'left':left+'px'}).focus();
}

function tooltipRightOpen ( targetTipBox, top, right )  {
    targetTipBox.show().css({'top':top+'px', 'left':right+'px'}).focus();
}

function tooltipClose ( targetTipBox ) {
    targetTipBox.removeAttr('style').hide();
    targetTipBox.removeClass('on');
}


/****** 체크박스 모두 선택 ******/
function checkAll () {
    var chkBtn = $('.ui-check-all');
    chkBtn.on('click',function () {
        var checked = $(this).is(':checked'),
            name = $(this).attr('name');

        if ( checked ) {
            $('[name="'+name+'"]:not(:disabled)').prop('checked',true)
        } else {
            $('[name="'+name+'"]').prop('checked',false)
        }
    });
}


/****** toggle ******/
function toggleCnt () {
    var toggleBtn = $('.ui-toggle-btn'),
        closeToggleBtn = $('.ui-close-toggle');

    //toggleBtn.on('click', function () {
    $(document).on('click','.ui-toggle-btn', function () {
        var toggleOn = $(this).hasClass('toggle-on'),
            toggleCnt = $(this).closest('.ui-toggle').find('.ui-toggle-content');
        toggleCntSub = toggleCnt.find('ui-toggle-sub');
        sortingBox = $(this).closest('.sorting-head').parent().hasClass('sorting-box');
        sortingBoxCnt = $(this).closest('.sorting-box').find('.ui-toggle-content');
        sortingBoxCntSub = sortingBoxCnt.find('ui-toggle-sub');

        if ( !toggleOn ) {
            $(this).addClass('toggle-on');
            $(this).closest('.ui-toggle').addClass('on');
            if(!sortingBox){
                toggleCnt.removeClass('hide');
                // 상품상세 리뷰보기 영역
                if ( $(this).children('span').hasClass('txt-change') ) {
                    $(this).children('span').text('접기');
                }
            } else {
                sortingBoxCnt.removeClass('hide');
                // 상품상세 리뷰보기 영역
                if ( $(this).children('span').hasClass('txt-change') ) {
                    $(this).children('span').text('접기');
                }
                if($(this).hasClass('btn-option-search')){
                    $(this).children('span').text('검색닫기');
                }
            }

        } else if ( toggleOn ) {
            $(this).removeClass('toggle-on');
            $(this).closest('.ui-toggle').removeClass('on');

            if(!sortingBox){
                toggleCnt.addClass('hide')
                // 상품상세 리뷰보기 영역
                if ( $(this).children('span').hasClass('txt-change') ) {
                    $(this).children('span').text('펼쳐보기');
                }
                // 조회영역 서브컨텐츠
                if ( !toggleCntSub.hasClass('hide') ) {
                    toggleCntSub.addClass('hide')
                }
            } else {
                sortingBoxCnt.addClass('hide');
                // 상품상세 리뷰보기 영역
                if ( $(this).children('span').hasClass('txt-change') ) {
                    $(this).children('span').text('펼쳐보기');
                }
                // 조회영역 서브컨텐츠
                if ( !sortingBoxCntSub.hasClass('hide') ) {
                    sortingBoxCntSub.addClass('hide')
                }
                if($(this).hasClass('btn-option-search')){
                    $(this).children('span').text('상세검색');
                }
            }
        }

        // 마이페이지 주문상품검색 > 목록 높이값 조절
        if ( $(this).parents('.layer-pop').hasClass('order-search-pop') ) {
            heightAuto () // 검색결과 목록 높이값 조절
        }

    })

    // closeToggleBtn.on('click', function () {
    $(document).on('click','.ui-close-toggle', function () {
        closeToggle(this)
    })

    function closeToggle (el) {
        $(el).parents('.ui-toggle').removeClass('on')
        $(el).parents('.ui-toggle-content').addClass('hide')
        $(el).parents('.ui-toggle').find('.ui-toggle-btn').removeClass('toggle-on')
    }
}


function heightAuto () {
    var elem = $('.ui-height-auto'),
        posTop = elem.position().top,
        wrapheight = $('.order-search-pop').height() - 135;

    elem.css('max-height', wrapheight - posTop)
    elem.children('.mCustomScrollBox').css('max-height', wrapheight - posTop)

    $(window).resize(function () {
        var elem = $('.ui-height-auto'),
            posTop = elem.position().top,
            wrapheight = $('.order-search-pop').height() - 135;

        elem.css('max-height', wrapheight - posTop)
        elem.children('.mCustomScrollBox').css('max-height', wrapheight - posTop)
    })
}


function toggleHeight () {
    var toggleCnt = $('.ui-toggle-height');
    toggleBtn = $('.ui-toggle-btn'),
        showHeight = $('.show-line').outerHeight();

    toggleHeightClose ();

    toggleBtn.on('click', function () {
        var toggleOn = $(this).hasClass('toggle-on');

        if ( !toggleOn ) {
            $(this).addClass('toggle-on')
            toggleCnt.removeAttr('style')
        } else if ( toggleOn ) {
            $(this).removeClass('toggle-on');
            toggleHeightClose ();
        }
    })

    function toggleHeightClose () {
        toggleCnt.css({
            overflow : 'hidden',
            height : showHeight
        })
    }
}

function toggleSlide () {
    var toggleBtn = $('.ui-slide-btn');

    toggleBtn.on('click', function () {
        var toggleWrap = $(this).parent().parent('.ui-toggle-slide'),
            toggleCnt = toggleWrap.find('.ui-slide-content'),
            toggleCntSub = $(this).parent().next('.ui-slide-subcontent'),
            toggleOn = $(this).hasClass('toggle-on'),
            rightAction = $(this).prev('.right-item1'),
            rightActionBtn = rightAction.find('.ico-arrfill-toggle');

        if ( !toggleOn ) {
            $(this).addClass('toggle-on');
            toggleWrap.addClass('active');
            toggleCnt.slideDown(400);

            // 주문/결제 페이지의 이용약관 영역
            if ( toggleWrap.hasClass('order-info-inner') ) {
                rightActionBtn.addClass('on');
                rightAction.find('.change-txt').text('내용감추기')
                if ( rightAction.find('.on-txt').length ) {
                    rightAction.find('.on-txt').hide();
                }
            }

            if ( toggleCntSub.length ) {
                toggleCntSub.slideDown(400);
                rightActionBtn.addClass('on')
            }

        } else if ( toggleOn ) {
            $(this).removeClass('toggle-on');
            toggleWrap.removeClass('active');
            toggleCnt.slideUp(400);

            // 주문/결제 페이지의 이용약관 영역
            if ( toggleWrap.hasClass('order-info-inner') ) {
                rightAction.find('.change-txt').text('내용보기')
                if ( rightAction.find('.on-txt').length ) {
                    rightAction.find('.on-txt').show();
                    rightActionBtn.removeClass('on')
                }
            }

            toggleWrap.find('.ui-slide-subcontent').slideUp(400).removeClass('active');
            toggleWrap.find('.ico-arrfill-toggle').removeClass('on')
            toggleWrap.find('.ui-slide-btn').removeClass('toggle-on');
            toggleWrap.find('.ui-toggle-slide').removeClass('active');

            if ( toggleCntSub.length ) {
                toggleCntSub.slideUp(400).removeClass('active');
                rightActionBtn.removeClass('on')

            }
        }
    })
}


/****** 주문결제 박스 스크롤 고정 ******/
function paymentFix () {
    sideFixArea = $('.side-fix-area');
    payBox = sideFixArea.children('.payment-info-box');
    payBoxPos = payBox.offset().top - 150; // 110

    $(document).on('scroll', function(){
        docScroll = $(document).scrollTop();
        contentHeight = $('.container').height(),
            footHeight = $(".footer").outerHeight() + 175; // 275
        payBoxHeight = payBox.height() + 62;

        if ( docScroll > payBoxPos ){
            sideFixArea.addClass('fixed');
            if ( docScroll >= contentHeight - footHeight ) { // - 50
                payBox.removeAttr('style');
                sideFixArea.addClass('static').find('.payment-info-box').css('top',-payBoxHeight);
                $('.container').addClass('payment-container');
            } else {
                sideFixArea.removeClass('static');
                payBox.css('top', docScroll-300);
                $('.container').removeClass('payment-container');
            }
        } else {
            sideFixArea.removeClass('fixed');
            payBox.removeAttr('style');
        }
    });
}


/****** 카드등록 목록 ******/
//var cardSwiper = null;

function cardSwiperSlide () {

    // if(cardSwiper != null) cardSwiper.destroy();

    var cardSwiper = new Swiper('.card-box-list', {
        //  spaceBetween: 25,
        spaceBetween: 20,
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

    //cardSwiper.init();
}

/****** 비교담기 ******/
function closeToastPop () {
    $('.toast-pop').fadeOut();
    setTimeout(function () {
        $('.toast-pop').remove();
    },500)
}

function compareScrollFix () {
    var compareFixArea = $('.compare-layer-pop'),
        contentHeight = $('.container').height(),

        docScroll = $(document).scrollTop();
    footHeight = $(".footer").outerHeight() + 200 // 275

    if ( docScroll <= contentHeight - footHeight ) {
        compareFixArea.addClass('fixed')
    } else {
        compareFixArea.removeClass('fixed');
    }

    //console.log( docScroll, contentHeight,footHeight )

    $(document).on('scroll', function(){
        docScroll = $(document).scrollTop();
        footHeight = $(".footer").outerHeight() + 200; // 275
        if ( docScroll <= contentHeight - footHeight ) {
            compareFixArea.addClass('fixed')
        } else {
            compareFixArea.removeClass('fixed')
        }

        //console.log( docScroll, contentHeight,footHeight )
    });
}


/****** 아코디언 ******/
function accordionList () {
    var listWrap =  $('.ui-accordion').children('.ui-accordion-list'),
        click = listWrap.children('li').find('.ui-accordion-click');

    click.on('click', function () {
        var view = $(this).next('.ui-accordion-view'),
            list = $(this).parent('li');

        if ( !list.hasClass('active') ) {
            list.addClass('active').siblings('li').removeClass('active').find('.ui-accordion-view').addClass('hide');
            view.removeClass('hide')
        } else if ( list.hasClass('active') ) {
            list.removeClass('active');
            view.addClass('hide')
        }
    });
}


/****** 목록, 내용 더보기 ******/
function moreListView () {
    var viewList = $('.ui-more-list'),
        moreBtn = viewList.next().children('.ui-more-btn')

    moreBtn.on('click', function () {
        listItem = $(this).parent().siblings('.ui-more-list').children('li');

        if ( !$(this).hasClass('on')) {
            listItem.not('.ui-show-item').addClass('ui-hidden-off')
            $(this).addClass('on').html('<span>닫기</span>');
        } else {
            listItem.not('.ui-show-item').removeClass('ui-hidden-off')
            $(this).removeClass('on').html('<i class="ico-plus"></i><span>더보기</span>');
        }
    });
}


/****** 파일첨부 ******/
function fileAdd () {
    var fileTarget = $('.filebox .upload-hidden');

    fileTarget.on('change', function(){
        fileAction (this)
    });

    $(document).on('click','.ui-file-delete', function () {
        fileRemove (this)
    })
}

function fileAction (el) {
    if(window.FileReader){
        var filename = $(el)[0].files[0].name;
    } else {
        var filename = $(el).val().split('/').pop().split('\\').pop();
    }
    // $(this).siblings('.upload-name').val(filename);

    //unselected = $(el).siblings('.upload-name').find('span').text();

    if (  $(el).parent('.filebox').hasClass('on') ) {
        $(el).siblings('.upload-name').find('span').text(filename);
        return false;
    }
    $(el).parent('.filebox').addClass('on');
    $(el).siblings('.upload-name').find('span').text(filename);
    $(el).siblings('.upload-name').append('<button type="button" class="btn-x-xs ui-file-delete"><i class="ico-x-grey"></i><span class="blind">삭제</span></button>');
}

function fileRemove (el) {
    // var unselected = $(el).siblings('.upload-name').find('span').text();
    $(el).parents('.filebox').removeClass('on');
    //$(el).siblings('.txt').text(unselected);
    $(el).siblings('.txt').text('');
    $(el).parent('.upload-name').siblings('.upload-hidden').val('');
    $(el).remove();
}


/****** 식단상담 슬라이드 ******/
function planSwiperSlide () {
    var swiper = new Swiper('.meal-plan-list', {
        spaceBetween: 20,
        observer: true,
        observeParents: true,
        slidesPerView : 2,
        slidesPerGroup : 1,
        centeredSlides: false,
        autoplay: false,
        clickable: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });

    $('.meal-plan-list .swiper-slide').click(function(){
        $('.meal-plan-list .swiper-slide').removeClass('swiper-slide-active');
        $(this).addClass('swiper-slide-active');
    })
}

function orderSwiperSlide () {
    var swiper = new Swiper('.order-swiper-list', {
        spaceBetween: 10,
        observer: true,
        observeParents: true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });
}

/****** 상품 상세 앵커 이동 ******/
function ankerMove(event) {
    $('html,body').animate({
        scrollTop: $($(event).attr('href')).offset().top - 148
    }, 500);
}

/****** 상품상세 탭, 옵션선택박스 ******/
function goodsTab () {
    var docHeight = $(window).height(),
        optionBox = $('.option-select-fix').find('.option-select-area'),
        optionBoxScroll = $('.ui-fix-option');

    optionBox.css('height',docHeight - 48);

    if ( optionBoxScroll.parents('.right-option-area').length ) {
        optionBoxScroll.css('max-height',docHeight - 390)
    } else {
        optionBoxScroll.css('max-height',docHeight - 590)
    }

    detailTab =  $('.ui-goods-tab');
    optionArea =  $('.option-select-fix');

    detailTab.find('a').on('click', function(){
        //20220321 수정
        if($(this).attr('href') == '#detail-section01'){
            $('html,body').animate({
                scrollTop: $($(this).attr('href')).offset().top - 90
            }, 500);
        }else {
            $('html,body').animate({
                scrollTop: $($(this).attr('href')).offset().top - 123
            }, 500);
        }

        $(this).parent("li").addClass("current");
        $(this).parent("li").siblings().removeClass("current");
    })


    $(document).on('scroll', function(){
        var docScroll = $(document).scrollTop(),
            tabTop =  $('.goods-detail-wrap').offset().top - 48;

        if ( docScroll >= tabTop ){
            detailTab.addClass('fixed');
            optionArea.addClass('fixed');
        } else if ( docScroll < tabTop ) {
            detailTab.removeClass('fixed');
            optionArea.removeClass('fixed');
        }

        contentHeight = $('.container').height(),
            footHeight = $(".footer").outerHeight() + 275;

        if ( docScroll >= contentHeight - optionBox.height() + 160 ) { // 300
            optionArea.addClass('static')
        } else {
            optionArea.removeClass('static')
        }

        // 스크롤 탭
        scrollTopChange ();
    });

    $(window).resize(function () {
        var docHeight = $(window).height();
        optionBox.css('height',docHeight - 48);
        if ( optionBoxScroll.parents('.right-option-area').length ) {
            optionBoxScroll.css('max-height',docHeight - 390)
        } else {
            optionBoxScroll.css('max-height',docHeight - 490)
        }
    })
}

function scrollTopChange () {
    var docScroll = $(document).scrollTop();

    $('[id^=detail-section]').each(function(){
        if( docScroll + 180 > $(this).offset().top ){

            var sectionName = $(this).attr('id');
            var scrollNav = $('.tab-detail a[href$="' + sectionName + '"]').parent('li');

            scrollNav.siblings('li').removeClass('current');
            scrollNav.addClass('current');
        }
    });
} //


/****** 상품상세 옵션선택 고정 ******/
function optionFix () {
    $(document).on('scroll', function(){
        var docScroll = $(document).scrollTop(),
            optionBox =  $('.option-select-fix'),
            optionTop = optionBox.offset().top - 48

        if( docScroll >= optionTop ){
            optionBox.addClass('fixed');
        } else {
            optionBox.removeClass('fixed');
        }
    });
}


/****** 상품상세 설명 전체보기 ******/
function moreArticle() {
    var detailContent = $('.ui-more-article'),
        detailBtn = $('.ui-all-content')

    detailBtn.on('click', function () {
        var toggleOn = $(this).hasClass('toggle-on');
        if ( !toggleOn ) {
            $(this).addClass('toggle-on');
            detailContent.addClass('active')
            $(this).children('span').text('상품설명 접기')
        } else if ( toggleOn ) {
            $(this).removeClass('toggle-on');
            detailContent.removeClass('active')
            $(this).children('span').text('상품설명 펼쳐보기')
        }
    })
}


/****** 장바구니 이동 팝업 ******/
function cartPopOpen (el) {
    $(el).siblings('.cart-pop').addClass('on')
}

function cartPopClose (el) {
    $(el).parents('.cart-pop').removeClass('on')
}


/****** review 상세 슬라이드 ******/
function reviewSwiperSlide () {
    var  reviewSwiper = new Swiper('.review-slide', { //.review-detail-slide
        slidesPerView : 1,
        centeredSlides: true,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        effect : false,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        nested: true,
        touchRatio:false,
    });

    var  reviewInnerSwiper = new Swiper('.inner-pop-slide', {
        slidesPerView : 1,
        centeredSlides: true,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        touchRatio: true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });

}


/****** toggleLayer ******/
function toggleLayer () {
    togglelayerBtn = $('.ui-togglelayer-btn');
    //  togglelayerBox = $('.ui-togglelayer-box');

    togglelayerBtn.on('click',function () {

        if ( !$(this).next('.ui-togglelayer-box').length ) {
            togglelayerBox = $(this).parent().siblings('.ui-togglelayer-box');
        } else {
            togglelayerBox = $(this).next('.ui-togglelayer-box');
        }


        if ( !togglelayerBox.hasClass('on') ) {
            $(this).addClass('toggle-on');
            togglelayerBox.addClass('on');
        } else if ( togglelayerBox.hasClass('on') ) {
            $(this).removeClass('toggle-on');
            togglelayerBox.removeClass('on')
        }

        // 브랜드소개. 푸터메뉴일 경우 레이어 닫힘
        if (  $(this).parent().hasClass('brand-view-added') || $(this).parent().hasClass('family-select') ) {
            $('body').on('click',function(e){
                if($(e.target).closest('.ui-togglelayer-btn').length === 0 && $('.ui-togglelayer-btn').hasClass('toggle-on')){
                    togglelayerBox.removeClass('on');
                    togglelayerBtn.removeClass('toggle-on');
                }
            })
        }
    })
}


/****** 브랜드 검색 ******/
function brandSearch () {
    brandsearchBtn = $('.ui-brand-search');
    brandsearchArea = $('.brand-search-area');
    brandDim = brandsearchArea.find('.dim');

    brandsearchBtn.on('click',function () {
        docH = $(document).height() - 185;
        if ( !brandsearchArea.hasClass('on') ) {
            $(this).addClass('on');
            brandsearchArea.addClass('on');
            brandDim.css('height',docH)
        } else if ( brandsearchArea.hasClass('on') ) {
            brandSearchClose ()
        }
    })

    brandDim.on('click', function () {
        brandSearchClose ()
    })
}

function brandSearchClose () {
    brandsearchBtn.removeClass('on')
    brandsearchArea.removeClass('on');
    brandDim.removeAttr('style')
}


/****** 브랜드관 슬라이드 ******/
function brandSwiperSlide () {
    $('.brand-swiper-list').each(function(index, element){
        var $this = $(this);
        $this.addClass('brand-swiper-' + index);

        var brandSetColum4 = {
            spaceBetween: 20,
            observer: true,
            observeParents: true,
            slidesPerGroup : 4,
            slidesPerView : 4 ,
            touchRatio: false,
            watchOverflow: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        }

        var brandSetColum5 = {
            spaceBetween: 25,
            observer: true,
            observeParents: true,
            slidesPerGroup : 5,
            slidesPerView : 5,
            touchRatio: false,
            watchOverflow: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        }

        var brandSetColum1= {
            spaceBetween: 0,
            observer: true,
            observeParents: true,
            slidesPerGroup :1,
            slidesPerView : 1,
            touchRatio: false,
            watchOverflow: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        }

        var brandSetColum5_page = {
            spaceBetween: 25,
            observer: true,
            observeParents: true,
            slidesPerGroup : 5,
            slidesPerView : 5,
            touchRatio: false,
            watchOverflow: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
        }

        var brandSetColum1_page = {
            spaceBetween: 0,
            observer: true,
            observeParents: true,
            slidesPerGroup :1,
            slidesPerView : 1,
            touchRatio: false,
            watchOverflow: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
        }

        if ( $this.hasClass('column4') ) {
            var  brandSwiper = new Swiper('.brand-swiper-' + index, brandSetColum4)
        } else if ( $this.hasClass('column5') ) {
            var  brandSwiper = new Swiper('.brand-swiper-' + index, brandSetColum5)
            if ( $this.hasClass('page-slide-note') ) {
                var  brandSwiper = new Swiper('.brand-swiper-' + index, brandSetColum5_page)
            }
        } else if ( $this.hasClass('column1') ) {
            var  brandSwiper = new Swiper('.brand-swiper-' + index, brandSetColum1)
            if ( $this.hasClass('page-slide-note') ) {
                var  brandSwiper = new Swiper('.brand-swiper-' + index, brandSetColum1_page)
            }
        }
    });
}

/****** 이달의 특가 ******/
function anchorMove () {
    var anchorList = $('.ui-anchor-tab');

    anchorList.find('a').on('click', function(){
        if($(this).parents('li').index() == 0){
            var topH = 109;
        }
        else{
            if ( $(this).parents('.ui-anchor-tab').hasClass('select-nav-list') ) {
                var topH = 52;
            } else {
                var topH = 179;
            }
        }
        $('html,body').animate({
            scrollTop: $($(this).attr('href')).offset().top - topH
        }, 500);

        $(this).parents('.ui-anchor-tab').removeClass('on')
        $(this).parents('.ui-anchor-tab').siblings('.ui-togglelayer-btn').removeClass('toggle-on')
    });
}

function selectBarFix () {
    var cntHeight = $('.frame-sm').find('[class*=tab-grid-span]').height();
    var barTop =  $('.scroll-select-bar').offset().top + cntHeight;

    $(document).on('scroll', function(){
        var docScroll = $(document).scrollTop();

        if ( docScroll >= barTop ){
            $('.scroll-select-bar').addClass('fixed');
        } else if ( docScroll < barTop ) {
            $('.scroll-select-bar').removeClass('fixed');
            $('.scroll-select-bar').find('.ui-anchor-tab').removeClass('on')
            $('.scroll-select-bar').find('.ui-anchor-tab').siblings('.ui-togglelayer-btn').removeClass('toggle-on')
        }
        scrollSelectBarChange();
        setTimeout(function () {
            selectBarChange();
        },500)

    });
}

function scrollSelectBarChange () {
    var docScroll = $(document).scrollTop();

    $('[id^=special-div]').each(function(){
        if( docScroll + 180 > $(this).offset().top ){
            var sectionName = $(this).attr('id');
            var scrollNav = $('.select-nav-list a[href$="' + sectionName + '"]').parent('li');

            scrollNav.siblings('li').removeClass('on');
            scrollNav.addClass('on');
        }
    });
} //


function selectBarChange () {
    $('.select-nav-list').children('li').each(function () {
        var listValue = $(this).find('span').text();
        if ( $(this).hasClass('on') ) {
            $(this).parents('.select-nav-box').find('.select-nav-value').children('.txt').text(listValue)
        }
    });
}


/****** 베스트 랭킹 swiperTab  rankTabSwiperSlide ******/
function rankTabSwiperSlide () {
    var rankTabSwiper = new Swiper('.ranking-swiper-tab', {
        //spaceBetween: 34,
        slidesPerView : 11,
        centeredSlides: false,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        touchRatio:true,
    });
}
function rankThriftyTabSwiperSlide () {
    var rankTabSwiper = new Swiper('.ranking-swiper-thriftyTab', {
        slidesPerView : 9,
        centeredSlides: false,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        touchRatio:true,
    });
}
function rankStabSwiperSlide () {
    var rankTabSwiper = new Swiper('.ranking-swiper-stab', {
        // spaceBetween: 30,
        slidesPerView : 11,
        centeredSlides: false,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        touchRatio:true,
    });
}

//선물하기 카테고리
function cateSwiperSlide () {
    var cateSwiper = new Swiper('.gift-content .cate-swiper-tab', {
        slidesPerView: 'auto',
        slidesPerView : 8,
        autoplay: {
            delay: 4500,
            disableOnInteraction: false,
        },
        centeredSlides: false,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });

    // $('.swiper-button-ctrl').on('click', function () {
    //     if ( !$(this).hasClass('play') ) {
    //         cateSwiper.autoplay.stop();
    //         $(this).addClass('play')
    //         console.log('autoplay stop')
    //     } else {
    //         cateSwiper.autoplay.start();
    //         $(this).removeClass('play')
    //         console.log('autoplay start')
    //     }
    // });

    $('.cate-swiper-tab').find('.swiper-slide').click(function(e){
        e.preventDefault();
        $(this).siblings('.swiper-slide').removeClass('current');
        $(this).addClass('current');
    });
}

//선물하기 카테고리
function cateSwiperSlide2 () {
    if($('.gift-sub-content .cate-swiper-tab .swiper-slide').length < 11){
        $('.cate-menu-box').addClass('size-auto')
    }
    var cateSwiper2 = new Swiper('.gift-sub-content .cate-swiper-tab', {
        slidesPerView: 'auto',
        slidesPerView : 11,
        centeredSlides: false,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });

    $('.cate-swiper-tab').find('.swiper-slide').click(function(e){
        e.preventDefault();
        $(this).siblings('.swiper-slide').removeClass('current');
        $(this).addClass('current');
    })
}

//배너
function bannerDefaultSwiper () {
    var bannerDefaultSlide = new Swiper('.banner-default-swipe .swiper-container', {
        spaceBetween: 20,
        slidesPerView : 1,
        observer: true,
        observeParents: true,
        centeredSlides: true,
        autoplay: {
            delay: 4000,
            disableOnInteraction: false,
        },
        pagination: {
            el: '.swiper-pagination',
            type: 'fraction',
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });

    // $('.swiper-button-ctrl').on('click', function () {
    $('.banner-default-swipe').find('.swiper-button-ctrl').on('click', function () {
        if ( !$(this).hasClass('play') ) {
            bannerDefaultSlide.autoplay.stop();
            $(this).addClass('play')
            //console.log('autoplay stop')
        } else {
            bannerDefaultSlide.autoplay.start();
            $(this).removeClass('play')
            //console.log('autoplay start')
        }
    })
}

/****** 1팩담기 ******/
function onepackLayer (el) {
    if ( !$(el).parents('.onepack-layer-box').hasClass('toggle-on') ) {
        $(el).parents('.onepack-layer-box').addClass('toggle-on')
    } else if ( $(el).parents('.onepack-layer-box').hasClass('toggle-on') ) {
        $(el).parents('.onepack-layer-box').removeClass('toggle-on')
    }
}



/****** 베스트 랭킹 bestPrdSwiperSlide ******/
function bestPrdSwiperSlide () {
    var bestPrdSwiper = new Swiper('.best-prd-list', {
        spaceBetween: 20,
        slidesPerGroup :5,
        slidesPerView : 5,
        centeredSlides: false,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });
}


/****** 상품카테고리 탭 ******/
function scrollFixTab () {
    var area = $('.scroll-fix-tab'),
        areaInner = area.children('.inner-area'),
        areaMap = areaInner.find('.line-map'),
        areaTop = area.offset().top - 50,
        boxHeight = $('.ui-togglelayer-box').outerHeight() + 40;

    $(document).on('scroll', function(){
        var docScroll = $(document).scrollTop();

        if ( docScroll >= areaTop ){
            $('.content-wrap').css('padding-top',boxHeight)
            area.addClass('fixed');
        } else if ( docScroll < areaTop ) {
            $('.content-wrap').removeAttr('style')
            area.removeClass('fixed');
            $('.ui-togglelayer-btn').removeClass('toggle-on');
            $('.ui-togglelayer-box').removeClass('on');
        }

    });
}


/****** 상품 목록 이벤트 슬라이드 배너 ******/
function eventSwiperSlide () {
    var eventSwiper = new Swiper('.prd-event-slide', {
        spaceBetween:40,
        slidesPerView : 2,
        centeredSlides: false,
        observer: true,
        observeParents: true,
        watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        touchRatio:true,
    });
}


/****** 상품 목록 상세검색 ******/
function prdDetailSearch () {

    $('.brand-check-list').each(function () {
        listItem = $(this).children('li'),
            max = 21;

        if ( listItem.length > max ) {
            $(this).parent('.scroll-area').addClass('scroll-on');
            $(this).parent('.scroll-area').mCustomScrollbar('destroy');
            $(this).parent('.scroll-area').mCustomScrollbar({
                scrollInertia: 200, //0
                scrollEasing: "easeOut",
                //  alwaysShowScrollbar : 2,
                mouseWheel:{ deltaFactor: 30 }
            });
        }
    });
}


/****** mCustomscrollbar ******/
function customScroll () {
    $('.ui-custom-scroll').mCustomScrollbar({
        scrollInertia: 200, //0
        scrollEasing: "easeOut",
        //  alwaysShowScrollbar : 2,
        mouseWheel:{ deltaFactor: 30 }
    });
}


/****** 등급별 쿠폰 couponLevelSlide ******/
function couponLevelSlide () {
    var couponLevelSwiper = new Swiper('.coupon-level-list', {
        //calculateHeight:true,
        autoHeight: true,
        slidesPerGroup :1,
        slidesPerView : 1,
        spaceBetween:16,
        observer: true,
        observeParents: true,
        //watchOverflow: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
}


/****** 쿠폰 내부 팝업 ******/
function openCouponInfo () {
    var btn = $('.ui-coupon-info'),
        close = $('.btn-x-sm2');

    btn.on('click', function () {
        var wrap = $(this).parents('.card-li-inner').find('.coupon-info-card');
        wrap.addClass('active');
    })

    close.on('click', function () {
        $(this).parent('.coupon-info-card').removeClass('active')
    })
}


/****** 쿠폰 더보기 ******/
function moreCouponView () {
    const listWrap = $('.ui-coupon-list').children('ul');

    listWrap.each(function () {
        $(this).children('li.colum').show();
    })

    /* 더보기 기능 숨김 처리(2023.01.26)
    var wrap = $('.coupon-wrap-box'),
        couponMoreBtn = $('.ui-coupon-more'),
        listWrap = $('.ui-coupon-list').children('ul');

    listWrap.each(function () {
        var couponLi = $(this).children('li').length;

        if ( couponLi > 4 ) {
            $(this).parent('.ui-coupon-list').find('.ui-coupon-more').show();
        } else {
            $(this).parent('.ui-coupon-list').find('.ui-coupon-more').hide();
        }
        $(this).children('li.colum').slice(0, 4).show();
    })

    couponMoreBtn.click(function(e){
        var height = $(this).parents('.swiper-wrapper').height();

        if ( !$(this).hasClass('active') ) {
            $(this).siblings('.grid-area-span2').find('li.colum:hidden').slice(0, 4).show();
            $(this).parents('.swiper-wrapper').css('height','auto')

            if( $(this).siblings('.grid-area-span2').find('li.colum:hidden').length === 0 ){
                $(this).addClass('active').html('<span>숨기기</span><i class="ico-arr-toggle"></i>')
            }
        } else {
            hideListItem()
        }
    });

    wrap.find('[class^=swiper-button-]').on('click',function () {
        hideListItem()
    })

    function hideListItem () {
        listWrap.find('li.colum:eq(3)').nextAll('li').hide();
        $('.ui-coupon-more').removeClass('active').html('<span>더보기</span><i class="ico-arr-toggle"></i>')
    }
    */
}

//선물하기 slide
function visualSlide(){
    var eventSwiper = new Swiper('.visual-slide .swiper-container', {
        slidesPerView : 1,
        spaceBetween: 20,
        observer: true,
        observeParents: true,
        loop: true,
        autoplay: {
            delay: 4000,
            disableOnInteraction: false,
        },
        pagination: {
            el: '.swiper-pagination',
            type: 'fraction',
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        watchOverflow: true,
    });


    //$('.swiper-button-ctrl').on('click', function () {
    $('.visual-slide').find('.swiper-button-ctrl').on('click', function () {
        if ( !$(this).hasClass('play') ) {
            eventSwiper.autoplay.stop();
            $(this).addClass('play')
            //console.log('autoplay stop')
        } else {
            eventSwiper.autoplay.start();
            $(this).removeClass('play')
            //console.log('autoplay start')
        }
    });
}

//메시지 카드
function messageSlide(){
    var galleryThumbs = new Swiper('.gallery-thumbs', {
        spaceBetween: 10,
        slidesPerView: 8,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
    });
    var galleryTop = new Swiper('.gallery-top', {
        spaceBetween: 10,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        on: {
            activeIndexChange: function () {
                $(".message-swiper .gallery-thumbs li").eq(this.realIndex).trigger('click');
            }
        },
        thumbs: {
            swiper: galleryThumbs
        }
    });
}

//상품상세
function goods_Slide(){
    var goodsThumbs = new Swiper('.goods-thumbs', {
        spaceBetween: 10,
        slidesPerView: 5,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
    });
    var galleryTop = new Swiper('.goods-top', {
        spaceBetween: 10,
        thumbs: {
            swiper: goodsThumbs
        },
        effect: 'fade',
        fadeEffect: {
            crossFade: false
        },
        on : {
            slideChange : function (current) {
                var index_currentSlide = galleryTop.realIndex;
                var currentSlide = galleryTop.slides[index_currentSlide];
                var videoWrap = $(currentSlide).children('.video-wrap');
                if(videoWrap.length >0){
                    if(videoWrap.hasClass('play')){
                        videoWrap.addClass('pause').removeClass('play')
                        if(videoWrap.find('.video-thumbnail').length >0){
                            videoWrap.find('iframe')[0].contentWindow.postMessage('{"event":"command","func":"' + 'pauseVideo' + '","args":""}', '*');
                        }
                    }else if(videoWrap.hasClass('pause')){
                        videoWrap.addClass('play').removeClass('pause')
                        if(videoWrap.find('.video-thumbnail').length >0){
                            videoWrap.addClass('hidden')
                            videoWrap.find('iframe')[0].contentWindow.postMessage('{"event":"command","func":"' + 'playVideo' + '","args":""}', '*');
                        }
                    }
                }
            },
        }
    });
}

//side event
function sideEvent(){
    var fired = 0;
    var winPos = $(this).scrollTop();
    var eTarget = $('.side-event-wide');
    var eWrap = $('.side-event');
    if(location.pathname == '/main' || location.pathname == '/'){
        eTarget.show().removeClass('content-min');
        eTarget.css({
            right: ($(window).innerWidth() - 1100) / 2,
            bottom: 0
        });
    }else{
        eWrap.parents('li').remove();
        eTarget.remove();
    };
    $(".side-event-wide .btn-close").click( function() {
        eWrap.show();
        var rightVal = $(window).innerWidth() - eWrap.offset().left - eWrap.outerWidth();
        var botVal = $(window).innerHeight() - (eWrap.offset().top - $(window).scrollTop()) - eTarget.outerHeight() / 2;
        eTarget.addClass('content-min');
        eTarget.stop().animate({
            right:rightVal,
            bottom:botVal
        },{
            duration:400,
            complete:function(){
                eTarget.hide();
                fired = 1;
                eWrap.slideDown(200);
                eWrap.delay(200).animate({
                    opacity:1
                })
            }
        });
        // eTarget.delay(400).hide();

    });

    $(".side-event .side-event-open").click( function() {
        if(!eTarget.is(':visible')) {
            eTarget.show();
            var rightVal = $(window).innerWidth() - eWrap.offset().left - eWrap.outerWidth();
            var botVal = $(window).innerHeight() - (eWrap.offset().top - $(window).scrollTop()) - eTarget.outerHeight() / 2;
            eWrap.animate({
                opacity:0
            },{
                duration:200,
                complete:function(){
                    eWrap.slideUp(200);
                }
            });
            eTarget.css({
                right: rightVal,
                bottom: botVal
            }, 400);
            eTarget.removeClass('content-min');
            eTarget.stop().animate({
                right: ($(window).innerWidth() - 1100) / 2,
                bottom: 0
            }, 400);

            fired = 0;
        };
    });

    $(window).bind('mousewheel',function(){
        $(window).on('scroll',function(){
            if($(window).scrollTop() > 500 && !fired){
                $(".side-event-wide .btn-close").stop().clearQueue().trigger('click');
                fired = 1;
            };
        });
    })
}


//최근본상품
function moveList(direction, elId, viewItemCont, scrollCont){
    var menu = document.getElementById(elId);
    var menuList = menu.getElementsByTagName('li');
    var menuCount = menuList.length;
    var startNo = 0;
    var sideTotal = $('.side-quick-area .move-total');
    var sideCrnt = $('.side-quick-area .move-current');

    style = (typeof(style) != 'undefined') ? style : 'block';

    for(var i=0;i<menuCount;i++){
        if(menuList[i].style.display == style){
            startNo = i;
            break;
        }
    }

    if(direction == 'next'){
        if(menuList[menuCount-1].style.display == style) return false;
        else{
            for(var i=0;i<menuCount;i++){
                if(i>=startNo + scrollCont && i<startNo + scrollCont + viewItemCont){
                    menuList[i].style.display = style;
                    $('.btn-left-xs').removeClass('disabled');
                    if(menuList[menuCount-1].style.display == style){
                        $('.btn-right-xs').addClass('disabled');
                    }
                }else{
                    menuList[i].style.display = 'none';
                }
            }
        }
        sideCrnt.text(Math.ceil(startNo / 3) + 2);
    }else if(direction == 'prev'){
        if(menuList[0].style.display == style) return false;
        else{
            for(var i=0;i<menuCount;i++){
                if(i>=startNo - scrollCont && i<startNo - scrollCont + viewItemCont){
                    menuList[i].style.display = style;
                    $('.btn-right-xs').removeClass('disabled');
                    if(menuList[0].style.display == style){
                        $('.btn-left-xs').addClass('disabled');
                    }
                }else{
                    menuList[i].style.display = 'none';
                }
            }
        }
        sideCrnt.text(Math.ceil(startNo / 3));
    };

    sideTotal.text(Math.ceil(menuCount / 3));
}
$(function(){
    var liCount = $('#recent-prod-list li').length;
    if(liCount<=3){
        $('.side-quick .btn-move-group .btn-right-xs').addClass('disabled')
    }
})

//이미지보기 슬라이드
function imgAutoSlide(){
    var imgAutoSwiper = new Swiper('.img-auto-slide .swiper-container', {
        autoHeight: true,
        observer: true,
        observeParents: true,
        spaceBetween : 1,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true
        }
    })
}
//상품상세 증정품 슬라이드
function defaultSlide(){
    var defaultSwiper = new Swiper('.default-swipe', {
        //autoHeight: true,
        spaceBetween : 30,
        observer: true,
        observeParents: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
        }
    })
}

//기본 탭 on/off
function defaultTab(){
    $('.ui-tab li a').on('click',function(e){
        e.preventDefault();
        $(this).parent('li').addClass('current').siblings('li').removeClass('current')
    })
}

//검색결과 구분
function sortingToggle(){
    $('.sorting-result li a').on('click',function(e){
        e.preventDefault();
        $(this).parent('li').addClass('active').siblings('li').removeClass('active');
    });
}

function goodsWithSlide () {
    var swiper = new Swiper('.with-goods', {
        spaceBetween: 50,
        slidesPerView : 5,
        slidesPerGroup : 5,
        observer: true,
        observeParents: true,
        centeredSlides: false,
        touchRatio: false,
        watchOverflow: true,
        autoplay: false,
        pagination: {
            el: '.swiper-pagination-page',
            type: 'fraction',
        },
        navigation: {
            nextEl: '.goods-btn-next',
            prevEl: '.goods-btn-prev'
        }
    });
}

function expFixed() {

    expFixArea = $('.exp-content .frame-sm');
    expBox = expFixArea.children('.frame-left');
    expBoxPos = expBox.offset().top - 120;

    $(document).on('scroll', function(){
        docScroll = $(document).scrollTop();
        contentHeight = $('.container').height(),
            footHeight = $(".footer").outerHeight() + 70;
        expBoxHeight = $(".frame-sm").height() - $(".frame-left").height();

        if ( docScroll > expBoxPos ){
            if ( docScroll >= contentHeight - footHeight ) {
                expBox.addClass('btm')
                expBox.removeClass('fixed')
            } else {
                expBox.removeClass('btm')
                expBox.addClass('fixed')
            }
        } else {
            expBox.removeClass('fixed')
        }
    });
}

//별별 랭킹
function starRankSlide(){
    var starRankSwiper = new Swiper('.star-rank-swiper', {
        slidesPerView: 3,
        slidesPerGroup: 3,
        spaceBetween : 40,
        observer: true,
        observeParents: true,
        touchRatio: false,
        watchOverflow: true,
        navigation: {
            nextEl: '.star-rank-btn-next',
            prevEl: '.star-rank-btn-prev'
        }
    });
}

// [Pub] 2022-05-19 : 키워드 텍스트 길이 체크 추가
function prodTxtLabelBox() {

    $('.form-inline-list [class*="custom-"]').each( function() {

        var txtLabel = $(this).find('label');
        txtLength = txtLabel.text().length;

        if (txtLength >= 9) {
            txtLabel.addClass('count-max');
        }
    });
}

// [Pub] 2022-05-19 추가 : 레이어 팝업 딤영역 클릭시 닫기
/*
  - 현재 함수 선언만 되어 있습니다.
  - 팝업'딤' 영역 클릭시 닫혀야할 팝업에,
    'dim-opt'클래스를 추가합니다.
    ex) class="layer-wrap dim-opt"

  - 만약 공통으로 적용할 경우 'layerPop()' 함수에 추가 바랍니다.
*/
function popLayerDim(){
    $(document).mouseup(function(e){
        var dimOpt = $('.layer-wrap.dim-opt');

        if(dimOpt.has(e.target).length === 0){
            dimOpt.hide();
        }
    });
}

// QA-2211
var noMouseUpIdList = ['app-down-popup', 'popup-compare', 'popup-cart', 'expressJusoSearchPop', 'modal-my-delivery',
    'member-join-certi-sms-pop', 'applyCounsel', 'icePop', 'popup-myAddress', 'popup-coupon']

$(document).mouseup(function (e) {
    try {
        let dimOpt = $('.tooltip-layer, .layer-pop, .layer-confirm');
        if (dimOpt.has(e.target).length === 0) {
            if (noMouseUpIdList.indexOf(e.target.id) > -1) return;
            const target = ".btn-close, .layer-pop:not(.main--view-popup) button[class=main-close-pop], i[class^=ico-x], .cancelAlert, .ui-close-pop, .btn-toggle.toggleCart";
            const applyCount = dimOpt.find(target).filter(function () {
                return this.clientHeight > 0;
            }).last().click().length;

            if (applyCount < 1) $(dimOpt.parentElement).first(target).click();
        }
    } catch (e) {}
});

function dimdDel(){
    $(".dimd").css("display",'none');
}



// confirms('메인 타이틀','서브 타이틀','타입 설정(okayCancel, okay, cancel)', 클릭반환 콜백);
function confirms(title,subTitle,type,callback){
    // 팝업 중복 방지
    if  ($('.dialog_overlay').length > 0){
        console.log('exist');
    }
    else {
        // 태그 생성 및 클래스 부여 후 출력
        let dialog_overlay = document.createElement('div')
        let alertWrap = document.createElement('div');
        let alertTitleWrap = document.createElement('div');
        let alertTitle = document.createElement('h2');
        let alertSubTitle = document.createElement('p');
        let alertBtnWrap = document.createElement('div');
        let alertCancel = document.createElement('button');
        let alertOkay = document.createElement('button');
        let alertCancelText = document.createElement('span');
        let alertOkayText = document.createElement('span');
        dialog_overlay.className ="dialog_overlay";
        alertTitle.innerText = (title);
        alertSubTitle.innerText = (subTitle);
        alertWrap.id = 'alert_wrap';
        alertWrap.className = 'alert_wrap_box';
        alertTitleWrap.className = 'alertTitleWrap';
        alertBtnWrap.className = 'alertBtnWrap';
        alertTitle.className = 'alert_title';
        alertSubTitle.className = 'alert_sub_title';
        alertCancel.className = 'alertCancel';
        alertOkay.className = 'alertOkay';
        alertCancelText.innerText = ("취소");
        alertOkayText.innerText = ("확인");
        document.body.appendChild(dialog_overlay);
        dialog_overlay.appendChild(alertWrap);
        alertWrap.appendChild(alertTitleWrap);
        alertWrap.appendChild(alertBtnWrap);
        alertTitleWrap.appendChild(alertTitle);
        alertTitleWrap.appendChild(alertSubTitle);


        // 타입 설정 okayCancel, okay, cancel (확인 취소, 확인, 취소 );
        if(type == "okayCancel"){
            alertBtnWrap.appendChild(alertCancel);
            alertBtnWrap.appendChild(alertOkay);
            alertCancel.appendChild(alertCancelText);
            alertOkay.appendChild(alertOkayText);

        }else if(type == "okay"){
            alertBtnWrap.appendChild(alertOkay);
            alertOkay.appendChild(alertOkayText);
            $(".alertOkay").css({
                width : "100%",
                "border-right" : "none"
            });
        }else if(type == "cancel"){
            alertBtnWrap.appendChild(alertCancel);
            alertCancel.appendChild(alertCancelText);
            $(".alertCancel").css({
                width : "100%",
                "border-right" : "none"
            });
        }

        var subTitleText = alertSubTitle.innerHTML;
        if(subTitleText == ''){
            $(".alert_title").css("padding-bottom",'0');
        }

        // 클릭 반환 및 창 닫기
        alertOkay.onclick = function() {
            $(".dialog_overlay").remove();
            if(callback !== null){callback(true);}

        };

        alertCancel.onclick = function() {
            $(".dialog_overlay").remove();
            if(callback !== null){callback(false);}
        };

    }

}


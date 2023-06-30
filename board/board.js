
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




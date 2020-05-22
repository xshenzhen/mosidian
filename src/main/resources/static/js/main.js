$(function(){

    "use strict";

    //===== Prealoder

    $(window).on('load', function(event) {
        $('.preloader').delay(500).fadeOut(500);
    });



    //===== Sticky

    $(window).on('scroll',function(event) {
        var scroll = $(window).scrollTop();
        if (scroll < 245) {
        $(".navbar").removeClass("sticky");
        }else{
        $(".navbar").addClass("sticky");
        }
    });



    //===== Mobile Icon active

    $('.navbar-toggler').on('click', function(event) {
        $(this).toggleClass("active");
    });

    // 弹出框
    $('#popup-box').popover({
        placement: "bottom",
        html: true,
        content: "<div id='pop_content' style='display: flex;justify-content: center;flex-direction: column;width: 300px;height: auto'></div>"
    }).click(function () {
        var html =
            // "  <a href=\"#\" class=\"\" style='padding: 0 8px;font-size: 15px;text-align: center'>\n" +
            // "    暂无数据\n" +
            // "  </a>\n" +
            "  <a href=\"#\" class=\"\" style='padding: 8px;font-size: 15px;border-bottom: 1px solid darkgrey;width: 250px;'><i class='iconfont icongouwuche' style='padding-right: 20px'></i>注册</a>\n" +
            "  <a href=\"#\" class=\"\" style='padding: 8px;font-size: 15px;border-bottom: 1px solid darkgrey;width: 250px;'><i class='iconfont iconlike' style='padding-right: 20px'></i>登录</a>\n" +
            "  <a href=\"#\" class=\"\" style='padding: 8px;font-size: 15px;border-bottom: 1px solid darkgrey;width: 250px;'><i class='iconfont iconsort' style='padding-right: 20px'></i>订单</a>\n" +
            "  <a href=\"#\" class=\"\" style='padding: 8px;font-size: 15px;border-bottom: 1px solid darkgrey;width: 250px;'><i class='iconfont iconfriend' style='padding-right: 20px'></i>收藏</a>\n" +
            "  <a href=\"#\" class=\"\" style='padding: 8px;font-size: 15px;border-bottom: 1px solid darkgrey;width: 250px;'><i class='iconfont iconfriendadd' style='padding-right: 20px'></i>售后服务</a>\n";
        $("#pop_content").html(html);
    });

    $('#bagBtn1').click(function () {
        if ($('.navBag1').css('display') == 'none') {
            $('.navBag2').css('display','block');
        } else {
            $('.navBag1').css('display','none');
            $('.navBag2').css('display','block');
        }
    });

    $('#bagBtn2').click(function () {
        if ($('.navBag2').css('display') == 'none') {
            $('.navBag1').css('display','block');
        } else {
            $('.navBag1').css('display','block');
            $('.navBag2').css('display','none');
        }
    });



    //===== Scroll It active

    $.scrollIt({
        upKey: 38,             // key code to navigate to the next section
        downKey: 40,           // key code to navigate to the previous section
        easing: 'linear',      // the easing function for animation
        scrollTime: 1300,      // how long (in ms) the animation takes
        activeClass: 'active', // class given to the active nav element
        onPageChange: null,    // function(pageIndex) that is called when page is changed
        topOffset: -70,        // offste (in px) for fixed top navigation
    });




    //===== SLIDER PART

    $('.slider_part').slick({
        autoplay:true,
        autoplaySpeed:6000,
        dots:true,
        arrows:false,
        fade:true,
        pauseOnHover:false,
    });


    //====== Magnific Popup

    $('.Video-popup').magnificPopup({
        type: 'iframe'
        // other options
    });


    //====== Team Slide Slick

    $('.team_slide').slick({
        autoplay: true,
        autoplaySpeed: 4000,
        speed: 300,
        prevArrow:'<i class="fas fa-angle-left"></i>',
        nextArrow:'<i class="fas fa-angle-right"></i>',
        slidesToShow: 3,
        slidesToScroll: 1,
        responsive: [
        {
          breakpoint: 1200,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 1,
          }
        },
        {
          breakpoint: 992,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1,
            arrows: false,
          }
        },
        {
          breakpoint: 768,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: false,
          }
        }
        // You can unslick at a given breakpoint now by adding:
        // settings: "unslick"
        // instead of a settings object
        ]
    });


    //====== Client Slide Slick

    $('.client-slied').slick({
        autoplay: true,
        autoplaySpeed: 4000,
        speed: 300,
        prevArrow:'<i class="fas fa-angle-left"></i>',
        nextArrow:'<i class="fas fa-angle-right"></i>',
        slidesToShow: 3,
        slidesToScroll: 1,
        responsive: [
        {
          breakpoint: 1200,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 1,
          }
        },
        {
          breakpoint: 992,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1,
            arrows: false,
          }
        },
        {
          breakpoint: 768,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: false,

          }
        }
        // You can unslick at a given breakpoint now by adding:
        // settings: "unslick"
        // instead of a settings object
        ]
    });


    //====== Brand Slied Slick

    $('.brand_slied').slick({
        autoplay: true,
        autoplaySpeed: 2000,
        speed: 300,
        slidesToShow: 6,
        slidesToScroll: 1,
        arrows: false,
        responsive: [
        {
          breakpoint: 1200,
          settings: {
            slidesToShow: 6,
            slidesToScroll: 1,
          }
        },
        {
          breakpoint: 992,
          settings: {
            slidesToShow: 4,
            slidesToScroll: 1,
          }
        },
        {
          breakpoint: 768,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 1,
          }
        },
        {
          breakpoint: 576,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1,
          }
        }
        // You can unslick at a given breakpoint now by adding:
        // settings: "unslick"
        // instead of a settings object
        ]
    });


    //===== Images Loaded

    $('.container').imagesLoaded( function() {
        //===== Isotope
        var $grid = $('.grid').isotope({
          // options
        });
        // filter items on button click
        $('.project_menu ul').on( 'click', 'li', function(event) {
          var filterValue = $(this).attr('data-filter');
          $grid.isotope({ filter: filterValue });
        });

        //for menu active class
        $('.project_menu ul li').on('click', function (event) {
            $(this).siblings('.active').removeClass('active');
            $(this).addClass('active');
            event.preventDefault();
        });
    });



    //===== Project Popup

    $('.project-popup').magnificPopup({
        type: 'image',
        gallery:{
        enabled:true
        }
    });



    //===== Back to top

    // Show or hide the sticky footer button
    $(window).on('scroll', function(event) {
        if($(this).scrollTop() > 600){
            $('.back-to-bottom').fadeIn(200)
            $('.back-to-top').fadeIn(200)
        } else{
            $('.back-to-top').fadeOut(200)
            $('.back-to-bottom').fadeOut(200)
        }
    });


    //Animate the scroll to yop
    $('.back-to-top').on('click', function(event) {
        event.preventDefault();

        $('html, body').animate({
            scrollTop: 0,
        }, 1500);
    });

    $('.home').on('click', function(event) {
        event.preventDefault();

        $('html, body').animate({
            scrollTop: 0,
        }, 1500);
    });


    $('.back-to-bottom').on('click', function(event) {
        event.preventDefault();

        $('html, body').animate({
            scrollTop: $('.footer').offset().top
        }, 1500);
    });

    // 业务系统
    $("#slide1").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business1.png) 260px 150px");
        $("#showh").html("车载物联—行车记录仪、车载MiFi、北斗定位导航、后视镜等");
        $("#showp1").html("客户诉求：车内设备多，要求可定制流量资费套餐和价格；信号覆盖要求稳定、 定位精确度高");
        $("#showp2").html("解决方案：移动/电信2-15G超低资费；工业级sim卡");
    })
    $("#slide2").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business2.png) 260px 150px");
        $("#showh").html("移动终端—物流跟踪、物流靶枪、无线POS机、智能手环、共享单车");
        $("#showp1").html("客户诉求：信号覆盖数量大、体积要求小、 功耗要求低、 并发数据大、 定位精度要求高、 通讯卡安全要求");
        $("#showp2").html("解决方案：移动/电信2G网络稳定、覆盖广，共享流量，通讯二合一减少功耗和体积，机卡锁定防安全，讯众专业物联网卡平台支持");
    })
    $("#slide3").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business3.png) 260px 150px");
        $("#showh").html("智能家居—智能手表、智能手环、智能眼镜");
        $("#showp1").html("客户诉求：具备大规模的卡管理能力，可监控每个终端的流量用量以及精确的定位能力");
        $("#showp2").html("解决方案：移动/电信2G网络稳定，机卡锁定防安全，专业物联网卡平台支撑多级管理");
    })
    $("#slide4").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business4.png) 260px 150px");
        $("#showh").html("共享经济—共享汽车、共享单车、共享充电桩、共享充电宝");
        $("#showp1").html("客户诉求：根据不同的流量消耗状况制定不同的资费套餐，帮助用户节能，需短信激活解锁");
        $("#showp2").html("解决方案：套餐共享池、流量池给予企业更灵活的方案选择，降低流量冗余、减少浪费");
    })
    $("#slide5").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business1.png) 260px 150px");
        $("#showh").html("车载物联—行车记录仪、车载MiFi、北斗定位导航、后视镜等");
        $("#showp1").html("客户诉求：车内设备多，要求可定制流量资费套餐和价格；信号覆盖要求稳定、 定位精确度高");
        $("#showp2").html("解决方案：移动/电信2-15G超低资费；工业级sim卡");
    })
    $("#slide6").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business2.png) 260px 150px");
        $("#showh").html("车载物联—行车记录仪、车载MiFi、北斗定位导航、后视镜等");
        $("#showp1").html("客户诉求：车内设备多，要求可定制流量资费套餐和价格；信号覆盖要求稳定、 定位精确度高");
        $("#showp2").html("解决方案：移动/电信2-15G超低资费；工业级sim卡");
    })
    $("#slide7").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business3.png) 260px 150px");
        $("#showh").html("车载物联—行车记录仪、车载MiFi、北斗定位导航、后视镜等");
        $("#showp1").html("客户诉求：车内设备多，要求可定制流量资费套餐和价格；信号覆盖要求稳定、 定位精确度高");
        $("#showp2").html("解决方案：移动/电信2-15G超低资费；工业级sim卡");
    })
    $("#slide8").bind("mouseover",function(){
        $("#show").css("background","url(img/business/business4.png) 260px 150px");
        $("#showh").html("车载物联—行车记录仪、车载MiFi、北斗定位导航、后视镜等");
        $("#showp1").html("客户诉求：车内设备多，要求可定制流量资费套餐和价格；信号覆盖要求稳定、 定位精确度高");
        $("#showp2").html("解决方案：移动/电信2-15G超低资费；工业级sim卡");
    })

});

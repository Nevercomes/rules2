<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html
        class=" js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg no-smil svgclippaths js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg no-smil svgclippaths"
        lang="en" style="height: auto;">
<!--<![endif]-->
<head>
    <title>中南大学-校规校纪系统</title>
    <!-- Meta -->
    <meta charset="utf-8">
    <%--<!-- Web Fonts -->--%>
    <%--<link--%>
    <%--href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=cyrillic,latin"--%>
    <%--rel="stylesheet" type="text/css">--%>
    <!-- CSS Global Compulsory -->
    <link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <link href="css/blocks.css" rel="stylesheet">
    <link href="css/one.style.css" rel="stylesheet">
    <!-- CSS Footer -->
    <link href="css/footers/footer-v7.css" rel="stylesheet">
    <!-- CSS Implementing Plugins -->
    <link href="plugins/animate.css" rel="stylesheet">
    <link href="plugins/line-icons/line-icons.css" rel="stylesheet">
    <link href="plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet">
    <link href="plugins/pace/pace-flash.css" rel="stylesheet">
    <link href="plugins/owl-carousel2/owl-carousel.css" rel="stylesheet">
    <link
            href="plugins/cube-portfolio/cubeportfolio/css/cubeportfolio.min.css"
            rel="stylesheet">
    <link
            href="plugins/cube-portfolio/cubeportfolio/custom/custom-cubeportfolio.css"
            rel="stylesheet">
    <link href="plugins/shhos/shhos.css" rel="stylesheet">
    <link href="plugins/image-hover/css/img-hover.css" rel="stylesheet">
    <link href="plugins/login-signup-modal-window/css/style.css"
          rel="stylesheet">
    <!-- CSS Theme -->
    <link href="css/app.style.css" rel="stylesheet">
    <!-- CSS Customization -->
    <link href="css/custom.css" rel="stylesheet">
    <!-- 全局JS -->
    <script src="plugins/jquery/jquery.min.js"></script>
    <script src="plugins/angular/angular.min.js"></script>
    <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="js/forms/login.js"></script>
    <script src="plugins/json/jquery.json.min.js"></script>
    <!-- JS Implementing Plugins -->
    <!-- 平滑滚动 -->
    <script src="plugins/smoothScroll/jquery.smooth-scroll.js"></script>
    <script src="js/smoothScroll/smoothScroll.js"></script>
    <!-- 平滑滚动结束 -->
    <!--模态框-->
    <script src="js/modalBox/loginModal.js"></script>
    <!--模态框结束-->
    <!-- 弹出框 -->
    <script src="js/popup/personalPopup.js"></script>
    <!-- 弹出框结束 -->
    <!--获得通知-->
    <script src="js/notice/getPicture.js"></script>
    <!--获得通知结束-->
    <!--题库-->
    <script src="js/modalBox/questionBankModal.js"></script>
    <!--题库结束-->
    <!-- 计时器 -->
    <script>
        function getRTime() {
            var EndTime = new Date('2017/6/13 15:00:00'); //截止时间
            var NowTime = new Date();
            var t = EndTime.getTime() - NowTime.getTime();
            var h = Math.floor(t / 1000 / 60 / 60 % 24);
            var m = Math.floor(t / 1000 / 60 % 60);
            var s = Math.floor(t / 1000 % 60);

//document.getElementById("t_d").innerHTML = d + "天"; 
            document.getElementById("t_h").innerHTML = h + "时";
            document.getElementById("t_m").innerHTML = m + "分";
            document.getElementById("t_s").innerHTML = s + "秒";
        }
        setInterval(getRTime, 1000);
    </script>
</head>
<body class="demo-lightbox-gallery  pace-done" id="body"
      data-target=".one-page-header" data-spy="scroll">
<!-- 顶栏 -->
<nav
        class="one-page-header navbar navbar-default navbar-fixed-top homeapp-header"
        data-role="navigation">
    <div class="container">
        <div class="menu-container page-scroll">
            <a class="navbar-brand" href="#body"> <img alt="中南大学"
                                                       src="img/logo.png">
            </a>
        </div>


        <!-- 导航栏 -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <div class="menu-container">
                <ul class="nav navbar-nav">
                    <li class="page-scroll"><a href="#notice">通知公告</a></li>
                    <li class="page-scroll"><a href="#questionsbank">在线题库</a></li>
                    <li class="page-scroll"><a href="#examination">报名考试</a></li>
                    <li class="page-scroll"><a href="#ranking">考试排名</a></li>
                    <li class="page-scroll"><a href="#integral">积分兑换</a></li>
                    <li class="page-scroll"><a href="#contact">联系我们</a></li>
                    <li class="page-scroll"><a href="#help">考试须知</a></li>
                    <li id="login-li"><a
                            class="btn-u btn-u-lg btn-u-green btn-u-upper rounded-2x"
                            href="#signup" target="_blank" data-toggle="modal"
                            data-target="#loginModal">登录</a></li>
                    <li style="display:none" id="personal-li"><a type="button"
                                                                 class="btn-u btn-u-lg btn-u-green btn-u-upper rounded-2x"
                                                                 data-toggle="personal" id="personal"
                                                                 onclick="personalPopover()">个人</a></li>
                </ul>
            </div>
        </div>
        <!-- 导航栏结束 -->
    </div>
    <!-- /.container --> </nav>


<!-- 登录按钮模态框 -->
<div class="modal fade" id="loginModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">x
                </button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="bs-example bs-example-form">
                    <div style="padding: 5px 160px 10px;">
                        <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-user-o"></i>
								</span> <input type="text" class="form-control" placeholder="学号" id="studentId">
                        </div>
                        <br>
                        <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-key"></i>
								</span> <input type="password" class="form-control" placeholder="密码" id="password">
                        </div>
                        <br>
                        <button type="button" class="btn btn-primary " data-dismiss="modal"
                                style="width: 250px" id="loginBT" onclick="login()" aria-hidden="true">登录
                        </button>
                    </div>
                    <hr>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- 做题模态框 -->
<div class="modal fade" id="titleModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">x
                </button>
                <label class="modal-title" id="subjectTitle">xxxx题目</label>
                <div id="CountMsg" class="HotDate">
                    <span id="t_h">00时</span> <span id="t_m">00分</span>
                    <span id="t_s">00秒</span>
                </div>
            </div>
            <div class="modal-body" style="overflow-y: scroll; height: 800px;">
                <form class="bs-example bs-example-form" method="post">
                    <div id="title"></div>
                    <div style="text-align: center;">
                        <button type="button" class="btn btn-primary"
                                style="width: 100px; text-align: center">提交
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- 顶栏结束 -->
<!-- 公告模块 -->
<section class="promo-section" id="intro">
    <div class="interactive-slider-v2">
        <div class="container">
            <div class="row">
                <div class="col-md-5 col-sm-12 col-xs-12 col-md-offset-1 promo-info">
                    <h1>
                        <strong>校规校纪</strong>系统
                    </h1>
                    <p>
                        <strong>校规校纪系统</strong>
                        使同学们参与考试更加的方便，省去了同学们由于参加考试寻找考场浪费的时间，让学生得到更充裕的时间做其他的事情
                    </p>
                    <p class="popover-options">
                        <a type="button" class="btn btn-primary btn-lg"><img
                                src="img/phone.png" width="25" height="22"> 手机App端</a>
                    </p>
                    <script>
                        $(function () {
                            $(".popover-options a")
                                .popover(
                                    {
                                        trigger: 'hover',
                                        html: true,
                                        content: "<img src='img/erWeiMa.jpg' width='120' height='120'>"
                                    });
                        });
                    </script>
                </div>
                <div class="col-md-6 col-sm-12 col-xs-12 banner-img overflow-h">
                    <img class="img-responsive animated slideInUp wow animated"
                         style="visibility: visible; animation-name: slideInUp;" alt="校规校纪"
                         src="img/schoolrules.png">
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 公告模块结束 -->
<!-- 通知栏 -->
<section id="notice">
    <div class="container-fluid with-bg-2">
        <div class="container margin-bottom-60">
            <div class="how-it-works">
                <div class="g-heading-v10 g-mb-65 text-center">
                    <h2 class="g-color-white">
                        <em class="block-name"> 通知公告</em>
                    </h2>
                    <p>这里将提供最新的消息，包括考试通知，考试结果，一系列你想要的答案</p>
                </div>
                <div class="col-md-5">
                    <div class="carousel slide carousel-v1" id="myCarousel">
                        <div class="carousel-inner" id="pictureNotice">
                            <div class="item active">
                                <img alt="" src="img/2.jpg">
                                <div class="carousel-caption">
                                    <p>1234</p>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-arrow">
                            <a class="left carousel-control" href="#myCarousel"
                               data-slide="prev"> <i class="fa fa-angle-left"></i>
                            </a> <a class="right carousel-control" href="#myCarousel"
                                    data-slide="next"> <i class="fa fa-angle-right"></i>
                        </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <table style="font-size: 14px">
                        <tbody>
                        <tr>
                            <td align="center" style="width: 5%"><i
                                    class="fa fa-hand-o-right"></i></td>
                            <td align="left" style="width: 80%"><a style="color: #999"
                                                                   href="#">【类型一】内容1.....</a></td>
                            <td align="center" style="width: 15%">2017-5-27</td>
                        </tr>
                        <tr>
                            <td align="center" style="width: 5%"><i
                                    class="fa fa-hand-o-right"></i></td>
                            <td align="left" style="width: 80%"><a style="color: #999"
                                                                   href="#">【类型二】内容2.....</a></td>
                            <td align="center" style="width: 15%">2017-5-27</td>
                        </tr>
                        <tr>
                            <td align="center" style="width: 5%"><i
                                    class="fa fa-hand-o-right"></i></td>
                            <td align="left" style="width: 80%"><a style="color: #999"
                                                                   href="#">【类型三】内容3.....</a></td>
                            <td align="center" style="width: 15%">2017-5-27</td>
                        </tr>
                        <tr>
                            <td align="center" style="width: 5%"><i
                                    class="fa fa-hand-o-right"></i></td>
                            <td align="left" style="width: 80%"><a style="color: #999"
                                                                   href="#">【类型四】内容4.....</a></td>
                            <td align="center" style="width: 15%">2017-5-27</td>
                        </tr>
                        <tr>
                            <td align="center" style="width: 5%"><i
                                    class="fa fa-hand-o-right"></i></td>
                            <td align="left" style="width: 80%"><a style="color: #999"
                                                                   href="#">【类型一】内容.....</a></td>
                            <td align="center" style="width: 15%">2017-5-27</td>
                        </tr>
                        <tr>
                            <td align="center" style="width: 5%"><i
                                    class="fa fa-hand-o-right"></i></td>
                            <td align="left" style="width: 80%"><a style="color: #999"
                                                                   href="#">【类型一】内容.....</a></td>
                            <td align="center" style="width: 15%">2017-5-27</td>
                        </tr>
                        <tr>
                            <td align="center" style="width: 5%"><i
                                    class="fa fa-hand-o-right"></i></td>
                            <td align="left" style="width: 80%"><a style="color: #999"
                                                                   href="#">【类型一】内容.....</a></td>
                            <td align="center" style="width: 15%">2017-5-27</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 通知栏结束 -->
<!-- 题库考试  -->
<section id="questionsbank">
    <div class="container">
        <div class="g-pt-90 g-pb-70">
            <div class="g-heading-v10 g-mb-65 text-center">
                <h2>
                    <em class="block-name">题库</em>
                </h2>
                <p>选择下方的模拟考试题库进行在线练习</p>
            </div>
            <div class="row equal-height-columns">
                <div class="col-md-2"></div>
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s;"
                     data-wow-duration="1.5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>校规校纪模拟考试</h3>
                        <br><br>
                        <div class="pricing-v9-footer">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                               data-toggle="modal" data-target="#titleModal" onclick="createQuestionBank('校规校纪模拟考试')">进入</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s; animation-delay: 0.5s;"
                     data-wow-duration="1.5s" data-wow-delay=".5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>错题重做</h3>
                        <br><br>
                        <div class="pricing-v9-footer">
                            <a
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                                    data-toggle="modal" data-target="#titleModal" onclick="createQuestionBank('错题重做')">进入</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    </div>
</section>
<!-- 题库结束  -->

<!-- 考试竞赛-->
<section id="examination">
    <div class="container-fluid">
        <div class="subscribe">
            <div class="g-heading-v10 g-mb-45">
                <h2>
                    <em class="block-name">考试和竞赛</em>
                </h2>
            </div>
            <div class="container">
                <div class="g-heading-v7 g-mb-40">
                    <p>请在下方的竞赛报名处选择你需要参加的考试，浏览必须的考试信息并点击报名按钮完成竞赛报名</p>
                    <p>请在下方的考试处选择你需要参加的考试，浏览必须的考试信息在规定时间内完成考试</p>
                </div>
                <div class="col-md-2 col-xs-6 col-2xs-12 g-mb-30"></div>
                <!-- /考试项目 -->
                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default">校规校纪第一次考试</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li>考试时间：2017/9/10-2017/9/20</li>
                            <li>考试要求：所有2017级本科生</li>
                            <li>参考书目：《中南大学本科生手册》</li>
                            <li>答题网址：www.csu.edu.cn</li>
                            <li>注意：使用本人校内门户学号和密码登录</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            是否考试：<span class="g-color-default">未考试</span>
                        </div>
                        <div class="pricing-v9-footer">
                            <a
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                                    data-toggle="modal" data-target="#titleModal"
                                    onclick="getRTime()">进入</a>
                        </div>
                    </div>
                </div>

                <!-- /考试项目 -->
                <!-- 竞赛项目 -->

                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default">校规校纪竞赛</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li>考试时间：2017/9/10-2017/9/20</li>
                            <li>考试要求：所有2017级本科生</li>
                            <li>参考书目：《中南大学本科生手册》</li>
                            <li>答题网址：www.csu.edu.cn</li>
                            <li>注意：使用本人校内门户学号和密码登录</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            是否报名：<span class="g-color-default">未报名</span>
                        </div>
                        <div class="pricing-v9-footer">
                            <a
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                                    href="#">报名</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- 考试报名结束 -->
<!-- 考试排名 -->
<section id="ranking">
    <div class="container-fluid">
        <div class="subscribe">
            <div class="g-heading-v10 g-mb-45">
                <h2>
                    <em class="block-name">考试成绩</em>
                </h2>
            </div>
            <div class="container">
                <div class="g-heading-v7 g-mb-40">
                    <p>请在下方了解已参加过的考试的成绩信息</p>
                </div>
                <div class="col-md-2 col-xs-6 col-2xs-12 g-mb-30"></div>
                <!-- 考试成绩项目 -->
                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default">校规校纪</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li>参赛总人数：100</li>
                            <li>考试开始时间：2017-06-13</li>
                            <li>考试结束时间：2017-07-12</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            你的成绩：<span class="g-color-default">91</span>
                        </div>
                    </div>
                </div>
                <!-- 考试排名项目结束 -->
                <!-- 考试成绩项目 -->
                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default">校规校纪</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li>参赛总人数：100</li>
                            <li>考试开始时间：2017-06-13</li>
                            <li>考试结束时间：2017-07-12</li>
                            <li>你的排名:100</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            你的成绩：<span class="g-color-default">91</span>
                        </div>
                    </div>
                </div>
                <!-- 考试排名项目结束 -->
            </div>
        </div>
    </div>
</section>
<!-- 考试排名结束 -->
<!-- 积分兑换 -->
<section id="integral">
    <div class="container">
        <div class="g-pt-90">
            <div class="g-heading-v10 g-mb-30 text-center">
                <h2>
                    <em class="block-name">积分兑换</em>
                </h2>
                <p>通过学习获得的积分可以兑换以下物品，物品会进行间断的更新。</p>
            </div>
            <div class="row equal-height-columns">
                <div class="col-md-3 short-info short-info-bg-1 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods1" src="img/exchangegoods1.jpg">
                    <div class="short-info-text">
                        <h2>物品1</h2>
                        <p>物品1</p>
                    </div>
                </div>
                <div class="col-md-3 short-info short-info-bg-2 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods2" src="img/exchangegoods3.jpg">
                    <div class="short-info-text">
                        <h2>物品2</h2>
                        <p>物品2</p>
                    </div>
                </div>
                <div class="col-md-3 short-info short-info-bg-3 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods3" src="img/exchangegoods1.jpg">
                    <div class="short-info-text">
                        <h2>物品3</h2>
                        <p>物品3</p>
                    </div>
                </div>
                <div class="col-md-3 short-info short-info-bg-4 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods4" src="img/exchangegoods2.jpg">
                    <div class="short-info-text">
                        <h2>物品4</h2>
                        <p>物品4</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 积分兑换结束 -->
<hr>
<!-- 页脚 -->
<section id="contact">
    <div class="container-fluid with-bg-2 no-side-padding">
        <div class="container">
            <div class="footer">
                <div class="row">
                    <div class="col-md-5">
                        <div class="g-heading-v10 g-mb-65">
                            <h2 class="g-color-white">
                                <em class="block-name"> 联系我们</em>问题和调查
                            </h2>
                            <p>您可以通过填写该表格向我们发送问题或评论，我们很想听听你的意见。我们会根据意见进行完善。</p>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <form class="sky-form contact-style" id="sky-form3" action=""
                              method="post" novalidate="novalidate">
                            <fieldset>
                                <div class="row">
                                    <div class="col-md-6 col-md-offset-0">
                                        <div class="margin-bottom-30">
                                            <input name="name" class="form-control" id="name" type="text"
                                                   placeholder="姓名">
                                        </div>
                                        <div class="margin-bottom-30">
                                            <input name="email" class="form-control" id="email"
                                                   type="text" placeholder="邮箱*">
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-md-offset-0">
                                        <div class="margin-bottom-30">
											<textarea name="message"
                                                      class="form-control g-textarea-noresize" id="message"
                                                      placeholder="消息内容" rows="4"></textarea>
                                        </div>
                                        <p>
                                            <button class="btn-u btn-u-lg btn-u-upper rounded-2x"
                                                    type="submit">提交
                                            </button>
                                        </p>
                                    </div>
                                </div>
                            </fieldset>
                            <div class="message">
                                <i class="rounded-x fa fa-check"></i>
                                <p>Your message was successfully sent!</p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <div class="cr-left">
                        <p>
                            <strong>微信小程序</strong>：中南大学校规校纪考试
                        </p>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <div class="cr-right">
                        <ul class="list-unstyled list-inline cr-list">
                            <li class="page-scroll"><a class="rounded" href="#notice"><span
                                    class="glyphicon glyphicon-pushpin"></span></a></li>
                            <li><a class="rounded" href="#"><span
                                    class="glyphicon glyphicon-link"></span></a></li>
                            <li class="popover-options"><a class="rounded"
                                                           data-placement="top"><span
                                    class="glyphicon glyphicon-qrcode"></span></a>
                                <script>
                                    $(function () {
                                        $(".popover-options a")
                                            .popover(
                                                {
                                                    trigger: 'hover',
                                                    html: true,
                                                    content: "<img src='img/erWeiMa.jpg' width='120' height='120'>"
                                                });
                                    });
                                </script>
                            </li>
                            <li><a class="rounded" href="mailto:????@????.com"><span
                                    class="glyphicon glyphicon-envelope"></span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 页脚结束 -->

<!-- JS Implementing Plugins -->
<script src="plugins/owl-carousel2/owl.carousel.min.js"></script>
</body>
</html>
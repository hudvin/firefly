<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <link href="${resource(dir: 'tag-it/css/', file: 'jquery.tagit.css')}" rel="stylesheet" type="text/css">
    <link href="${resource(dir: 'tag-it/css/', file: 'tagit.ui-zendesk.css')}" rel="stylesheet" type="text/css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>

    <script src="${resource(dir: 'tag-it/js/', file: 'tag-it.js')}"></script>



    <g:javascript library="application"/>

    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">


    <link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}" type="text/css">

    <link href="/firefly/static/bundle-bundle_bootstrap_head.css" type="text/css" rel="stylesheet" media="screen, projection" />


    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<r:layoutResources/>
<g:layoutBody/>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Firefly</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>

                    <sec:ifLoggedIn>
                        <li><g:link controller="uploader" action="index">Download</g:link></li>
                    </sec:ifLoggedIn>

                    <li><a href="#contact">Contact</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>

                <form class="navbar-form pull-right">
                    <sec:ifNotLoggedIn>
                        <a class="btn btn-success" href="${createLink(controller: 'login', action: 'auth')}">Sign in</a>
                        <a class="btn btn-success"
                           href="${createLink(controller: 'register', action: 'index')}">Register</a>
                    </sec:ifNotLoggedIn>
                    <sec:ifLoggedIn>
                        <g:set var="username" value="${sec.loggedInUserInfo(field: 'username')}"/>
                        <a class="btn btn-success" href="#">${username}</a>
                        <a class="btn btn-success"
                           href="${createLink(controller: 'logout', action: 'index',)}">Logout</a>
                    </sec:ifLoggedIn>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>

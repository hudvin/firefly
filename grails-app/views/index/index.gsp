<%--
  Created by IntelliJ IDEA.
  User: hudvin
  Date: 5/9/13
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap 101 Template</title>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}" type="text/css">
    <r:require modules="bootstrap"/>
    <r:require modules="bootstrap-responsive-css" />
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Project name</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
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
                    <a class="btn btn-success" href="${createLink(controller:'login', action:'auth')}">Sign in</a>
                    <a class="btn btn-success" href="${createLink(controller:'register', action:'index')}">Register</a>


                    %{--<input class="span2" type="text" placeholder="Email">--}%
                    %{--<input class="span2" type="password" placeholder="Password">--}%
                    %{--<button type="submit" class="btn">Sign in</button>--}%
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

<form class="form-search search-bar">
  <div class="input-append">
    <input type="text" class="search-query" placeholder="Enter your address here...">
    <button type="submit" class="btn btn-primary">
    Search <i class="icon-search icon-white"></i></button>
  </div>
</form>



<div class="span9 well offset2">
    <form class="form-search search-bar" accept-charset="UTF-8" action="" method="post">

        <div class="input-append">

            <input type="text" class="search-query span7" placeholder="Enter your address here...">
        <button type="submit" class="btn btn-primary">
            Search <i class="icon-search icon-white"></i></button>
         </div>
    </form>
</div>


%{--<div class="container">--}%
	%{--<div class="row">--}%
		%{--<div class="span4 offset4 well">--}%
			%{--<legend>Please Sign In</legend>--}%
          	%{--<div class="alert alert-error">--}%
                %{--<a class="close" data-dismiss="alert" href="#">×</a>Incorrect Username or Password!--}%
            %{--</div>--}%
			%{--<form method="POST" action="" accept-charset="UTF-8">--}%
			%{--<input type="text" id="username" class="span4" name="username" placeholder="Username">--}%
			%{--<input type="password" id="password" class="span4" name="password" placeholder="Password">--}%
            %{--<label class="checkbox">--}%
            	%{--<input type="checkbox" name="remember" value="1"> Remember Me--}%
            %{--</label>--}%
			%{--<button type="submit" name="submit" class="btn btn-info btn-block">Sign in</button>--}%
			%{--</form>--}%
		%{--</div>--}%
	%{--</div>--}%
%{--</div>--}%


<!-- Main hero unit for a primary marketing message or call to action -->
<!--
<div class="row">
    <div class="span4 offset4">
      <div class="well">
        <legend>Sign in to WebApp</legend>
        <form method="POST" action="" accept-charset="UTF-8">
            <div class="alert alert-error">
                <a class="close" data-dismiss="alert" href="#">x</a>Incorrect Username or Password!
            </div>
            <input class="span3" placeholder="Username" type="text" name="username">
            <input class="span3" placeholder="Password" type="password" name="password">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"> Remember Me
            </label>
            <button class="btn-info btn" type="submit">Login</button>
        </form>
      </div>
</div>
</div>
-->
%{--<div class="container">--}%
    %{--<form class="well form-inline" method="post">--}%
        %{--<input type="text" class="span10" placeholder="Ссылка на новость" name="new_link">--}%
        %{--<button type="submit" class="btn">Добавить</button>--}%
    %{--</form>--}%
%{--</div>--}%
</body>
</html>
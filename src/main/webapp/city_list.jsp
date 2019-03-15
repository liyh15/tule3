<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/city_list.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
<title>城市列表</title>
</head>
<body>
    <div id="city_list_out">
        <p>支持中文/拼音输入</p>
        <div id="city_list_title">
            <ul>
                <li style="background-color:#11d311;color:#FFFFFF">ABCD</li>
                <li>EFGH</li>
                <li>JKLM</li>
                <li>NOPQRS</li>
                <li>TUVWX</li>
                <li>YZ</li>
            </ul>
        </div>
        <div id="city_list_body">
            <div id="ABCD">
                <div id="A">
                   <span class="head">A</span>
                </div>
                <div id="B"><span class="head">B</span></div>
                <div id="C"><span class="head">C</span></div>
                <div id="D"><span class="head">D</span></div>
            </div>
            <div id="EFGH">
                <div id="E"><span class="head">E</span></div>
                <div id="F"><span class="head">F</span></div>
                <div id="G"><span class="head">G</span></div>
                <div id="H"><span class="head">H</span></div>
            </div>
            <div id="JKLM">
                <div id="J"><span class="head">J</span></div>
                <div id="K"><span class="head">K</span></div>
                <div id="L"><span class="head">L</span></div>
                <div id="M"><span class="head">M</span></div>
            </div>
            <div id="NOPQRS">
                <div id="N"><span class="head">N</span></div>
                <div id="P"><span class="head">P</span></div>
                <div id="Q"><span class="head">Q</span></div>
                <div id="R"><span class="head">R</span></div>
                <div id="S"><span class="head">S</span></div>
            </div>
            <div id="TUVWX">
                <div id="T"><span class="head">T</span></div>
                <div id="W"><span class="head">W</span></div>
                <div id="X"><span class="head">X</span></div>
            </div>
            <div id="YZ">
                <div id="Y"><span class="head">Y</span></div>
                <div id="Z"><span class="head">Z</span></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/city_list.js"></script>
</html>
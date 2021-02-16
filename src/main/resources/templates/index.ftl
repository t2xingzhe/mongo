<!DOCTYPE html>
<html>
<head lang="en">
    <title>Spring Boot Demo - FreeMarker</title>
    <link href="/css/index.css" rel="stylesheet">
    <script type="text/javascript" src="/jar/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/index.js"></script>
</head>
<body>
<h2>首页</h2>
<div>
<a href="/index?id=3152">s1</a>
<a href="/index?id=3793">real</a>
<a href="/index?id=50">GLORY QUEST</a>
<a href="/index?id=1509">moodyz</a>
<a href="/index?id=1219">idea</a>
<a href="/index?id=1227">attack</a>
<a href="/index?id=4469">kawaii</a>
<a href="/index?id=3890">premium</a>
<a href="/index?id=6329">fitch</a>
<a href="/index?id=5032">e-body</a>
<a href="/index?id=5238">oppai</a>
<a href="/index?id=6304">本中</a>
<a href="/index?id=45103">溜池</a>
<a href="/index?id=6393">盗撮</a>
<a href="/index?id=5552">痴女</a>
<a href="/index?id=4641">超有名女優</a>
<a href="/index?id=5665">ROOKIE</a>
<a href="/index?id=40136">pre</a>
<a href="/index?id=45276">SOD</a>
<a href="/index?id=40003">deep</a>
<a href="/index?id=40005">waap</a>
<a href="/index?id=1398">dosma</a>
<a href="/index?id=40041">tma</a>
<a href="/index?id=40039">爱丽丝</a>
<a href="/index?id=45914">switch</a>
<a href="/index?id=40185">real</a>
    <a href="/index?id=40014">グローリークエスト</a>
    <a href="/index?id=40047">メディアステーション</a>
    <a href="/index?id=45339">豊彦</a>
    <a href="/index?id=45434">S級素人</a>
</div>
<textarea  style="float: right;position: fixed;margin-left: 900px" class="magnet" rows="50" cols="130"></textarea >
        <ul>
<#list itemList as item>
    <div>
                <img class="img" src="${item.img!}" fan="${item.id!}"/>

                <div>${item.title!} --- ${item.id!} <a href = "/act?name=${item.actUrl!}&id=${item.sId!}">${item.act}</a></div>
            </div>
</#list>
        </ul>
<div style="margin:0 auto;">
    <#include "page.ftl"/>
<@pageShow total,page,"index",id/>
</div>

</body>
</html>
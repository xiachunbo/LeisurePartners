function pageBack(){var a=window.location.href;if(/#top/.test(a)){window.history.go(-2);window.location.load(window.location.href)}else{window.history.back();window.location.load(window.location.href)}}function skip(c){addCookie("pcm","1",1,"","jd.com");var b=c;if(b=="http://www.jd.com/#m"){var b=document.location.href;if(b.indexOf("http://m.jd.com/sale/mall")==0){var a=b.replace("http://m.jd.com/sale/mall","http://sale.jd.com/mall");a=a+"#m";window.location.href=a;return}else{if(b.indexOf("http://m.jd.com/sale/act")==0){var a=b.replace("http://m.jd.com/sale/act","http://sale.jd.com/act");a=a+"#m";window.location.href=a;return}else{window.location.href="http://www.jd.com/#m";return}}}window.location.href=b}function addCookie(c,e,a,f,d){var g=c+"="+escape(e);if(a!=""){var b=new Date();b.setTime(b.getTime()+a*24*3600*1000);g+=";expires="+b.toGMTString()}if(f!=""){g+=";path="+f}if(d!=""){g+=";domain="+d}document.cookie=g};
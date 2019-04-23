<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>  
   <html lang="en">  
   <head>  
   <title>中军创</title>  
   </head>  
  
   <body></body>  
  
   <script>  
  
   (function(){  
  
       var ua = navigator.userAgent.toLowerCase();  
  
       var t;  
  
       var config = {  
  
			   /*scheme:必须*/  
  
               scheme_IOS: 'zhongjunchuang://',  
  
               scheme_Adr: 'zjc://client',  
  
               Adr_download_url: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.seventc.zhongjunchuang',  
               
               Ios_download_url:'https://itunes.apple.com/us/app/id1192166297',
            		   
               timeout: 600  
  
       };  
  
  
  
       function openclient() {  
           var startTime = Date.now();  
           var ifr = document.createElement('iframe');  
           ifr.src = ua.indexOf('os') > 0 ? config.scheme_IOS : config.scheme_Adr;  
           ifr.style.display = 'none';  
           document.body.appendChild(ifr);  
  
           var t = setTimeout(function() {  
               var endTime = Date.now();  
               if (!startTime || endTime - startTime < config.timeout + 200) {  
            	   if(ua.indexOf('os') > 0){
                   		window.location = config.Ios_download_url;  
            	   }else{
            		   window.location = config.Adr_download_url;
            	   }
               } else {  
  
               }  
  
           }, config.timeout);  
  
           window.onblur = function() {  
               clearTimeout(t);  
           }  
       }  
       openclient();  
   })()  
   </script>  
  
   </html>  


// body
$(document).ready(function(){
	
    $("section").on("mousewheel",wheely);
    
    function wheely(e){
    
        $("section").off("mousewheel");
        let y = e.originalEvent.deltaY;
        
        if(y>0){				
            let scr = $(this).next().offset().top;
            
        }else if(y<0){
            let scr = $(this).prev().offset().top;
            
        }
        $("html,body").stop().animate({
            "scrollTop":scr
        },1300,function(){
            $("section").on("mousewheel",wheely);
        });	
        return false;
    }

});


// nav 라인
$(function(){
		
    $(window).scroll(function(){
        let s = $(window).scrollTop();
        
        let max = $(document).height()-$(window).height();

        let result = (s/max)*100
        
        $(".line").css("width",result+"%");
        
        console.log(s,max);
    });

});



//아래에서  위
document.addEventListener("DOMContentLoaded", function() {
    const animateElements = document.querySelectorAll('.animate');

    const observerOptions = {
        root: null,
        rootMargin: '0px',
        threshold: 0.2 // 20%가 보일 때 애니메이션 시작
    };

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('show'); 
                observer.unobserve(entry.target); 
            }
        });
    }, observerOptions);

    animateElements.forEach(element => {
        observer.observe(element); 
    });
});




// header
$(document).ready(function(){
	$(window).resize(function(){
			
			let h = $(window).height();
			
			$(".cover").height(h);
			
			let w = $(window).width();		
			
	});

    $(window).trigger("resize");
		
		
    setTimeout(function(){
        
        $(".start_cover").stop().fadeOut(600);
        
        $("cover").trigger("play");
        
    },3000);
});


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

document.addEventListener("DOMContentLoaded", function() {
    const navbar = document.querySelector('.navbar');

    window.addEventListener('scroll', function() {
        if (window.scrollY > 50) { // 스크롤 위치가 50px을 넘으면
            navbar.classList.add('scrolled'); // 배경색 변경 클래스 추가
        } else {
            navbar.classList.remove('scrolled'); // 클래스 제거
        }
    });
});




// se1 아래에서  위
document.addEventListener("DOMContentLoaded", function() {
    const animateElements = document.querySelectorAll('.animate');

    const observerOptions = {
        root: null,
        rootMargin: '0px',
        threshold: 0.3 // 20%가 보일 때 애니메이션 시작
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

// se2
document.addEventListener("DOMContentLoaded", function() {
    const se2pa = document.getElementById("se2pa");

    function checkVisibility() {
        const rect = se2pa.getBoundingClientRect();
        const windowHeight = window.innerHeight;

        // 요소가 뷰포트 안에 들어왔는지 확인
        if (rect.top < windowHeight && rect.bottom > 0) {
            se2pa.classList.add("visible"); // 보일 때 애니메이션 클래스 추가
        } else {
            se2pa.classList.remove("visible"); // 보이지 않을 때 애니메이션 클래스 제거
        }
    }

    // 스크롤 및 리사이즈 이벤트 리스너 추가
    window.addEventListener("scroll", checkVisibility);
    window.addEventListener("resize", checkVisibility);
    
    // 페이지 로드 시 초기 확인
    checkVisibility();
});



document.addEventListener("DOMContentLoaded", function() {
    const images = document.querySelectorAll('.se2img');

    function checkVisibility() {
        const windowHeight = window.innerHeight;

        images.forEach(image => {
            const rect = image.getBoundingClientRect();
            if (rect.top < windowHeight && rect.bottom > 0) {
                image.classList.add("visible"); // 보일 때 클래스를 추가
            }
        });
    }

    window.addEventListener("scroll", checkVisibility);
    window.addEventListener("resize", checkVisibility);
    
    // 페이지 로드 시 초기 확인
    checkVisibility();
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
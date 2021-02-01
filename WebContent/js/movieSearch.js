/**
 * Movie Search Event JavaScript File
 */
var mv = function() {
	
	return {

		/**
		 * 영화검색 요청
		 * 
		 * @param {String} q 검색어 (필수)
		 * @param {String} p 페이지번호
		 */
		search : function(q, p) {
			
			if(q.trim() === '') {
				alert('검색어를 입력하세요.');
				return;
			}
			
			$.ajax({
				type : 'POST',
				dataType : 'html',
				url : '/search.do',
				data : {'q' : q, 'p' : p},
				success : function(data) {
					$('#contentArea').html(data).show();
				}
			});
			
		},

		/**
		 * 영화정보 상세보기
		 * <br>
		 * 목록영역 숨김 후 상세영역의 검색목록순번 영역 보이게 처리
		 * 
		 * @param {String} no 검색목록순번
		 */
		detail : function(no) {
			$('#listArea').hide();
			$('#detailArea').show();
			$('#detailArea').find('div[id^="detail"]').hide();
			$('#detail' + no).show();
		},
		
		/**
		 * 영화목록보기
		 * <br>
		 * 상세영역 숨김 후 목록영역 보이게 처리
		 */
		backList : function() {
			$('#detailArea').hide();
			$('#listArea').show();
			$('#detailArea').find('div[id^="detail"]').hide();
		},
		
		/**
		 * 북마크 저장 요청
		 * <br/>
		 * 검색목록순번에 해당하는 북마크저장 폼객체를 전송
		 * <br/>
		 * 폼객체정보 : 섬네일,제목,평점,개봉일,영화정보URL
		 * 
		 * @param {String} no 검색목록순번
		 */
		save : function(no) {
			
			var f = document.forms['save' + no];
			
			$.ajax({
				type : 'POST',
				dataType : 'json',
				url : '/bookmark/save.do',
				data : $(f).serialize(),
				success : function(json) {
					if(json.result && json.result == 'SUCCESS') {
						alert('저장되었습니다.');
					}else {
						alert('저장 시 오류가 발생하였습니다.');
					}
				}
			});
			
		},
		
		/**
		 * 북마크 삭제 요청
		 * 
		 * @param {String} seq 북마크 PK
		 */
		remove : function(seq) {
			
			$.ajax({
				type : 'POST',
				dataType : 'json',
				url : '/bookmark/delete.do',
				data : {'seq' : seq},
				success : function(json) {
					if(json.result && json.result == 'SUCCESS') {
						alert('삭제되었습니다.');
						mv.bookmark();
					}else {
						alert('삭제 시 오류가 발생하였습니다.');
					}
				}
			});
			
		},
		
		/**
		 * 북마크 목록조회 요청
		 * 
		 * @param {String} p 페이지번호
		 * @param {String} o 정렬순서(ASC:오름차순, DESC:내림차순)
		 * @param {String} s 정렬필드
		 */
		bookmark : function(p, o, s) {
			
			$.ajax({
				type : 'GET',
				dataType : 'html',
				url : '/bookmark/list.do',
				data : {'p' : p, 's' : s, 'o' : o},
				success : function(data) {
					$('#contentArea').html(data).show();
				}
			});
			
		}
	
	}
}();
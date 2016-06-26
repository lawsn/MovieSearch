/**
 * 
 */
var ssm = function() {
	
	return {
		search2 : function(q, p) {
			
			if(q.trim() === '') {
				alert('검색어를 입력하세요.');
				return;
			}
			
			$.ajax({
				type : 'POST',
				dataType : 'json',
				url : '/search2.do',
				data : {'q' : q, 'p' : p},
				success : function(data) {
					alert(data.channel);
					$('#contentArea').html(data);
				}
			});
			
		},
		
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
		
		detail : function(no) {
			$('#listArea').hide();
			$('#detailArea').show();
			$('#detailArea').find('div[id^="detail"]').hide();
			$('#detail' + no).show();
		},
		
		backList : function() {
			$('#listArea').show();
			$('#detailArea').hide();
			$('#detailArea').find('div[id^="detail"]').hide();
		},
		
		save : function(no) {
			
			var f = document.forms['save' + no];
			
			$.ajax({
				type : 'POST',
				dataType : 'html',
				url : '/bookmark/save.do',
				data : $(f).serialize(),
				success : function(data) {
					if(data == 'SUCCESS') {
						alert('저장되었습니다.');
					}
				}
			});
			
		},
		
		remove : function(seq) {
			
			$.ajax({
				type : 'POST',
				dataType : 'html',
				url : '/bookmark/delete.do',
				data : {'seq' : seq},
				success : function(data) {
					if(data == 'SUCCESS') {
						alert('삭제되었습니다.');
						ssm.bookmark();
					}
				}
			});
			
		},
		
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
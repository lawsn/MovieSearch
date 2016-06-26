package kr.lawsn.ssm.utils;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

public class SimpleTest {

	@Test
	public void parseJson() {

		
		//String requestgetparameter = "{'year':[{'link':'','content':'2015'}],'nation':[{'link':'','content':'러시아'},{'link':'','content':'에스토니아'},{'link':'','content':'우크라이나'}],'wiki_url':[{'link':'','content':''}],'expect_grades':[{'link':'http://movie.daum.net/moviedetailNetizenPoint.do?movieId=96284&type=before','content':''},{'link':'','content':'0'}],'video':[{'link':'','content':''}],'grade3':[{'link':'','content':''}],'grades':[{'link':'http://movie.daum.net/moviedb/grade?movieId=96284&type=netizen','content':''},{'link':'','content':'0'}],'title':[{'link':'http://movie.daum.net/moviedb/main?movieId=96284','content':'키예프/모스크바. 파트 1'}],'photo2':{'link':'http://movie.daum.net/moviedb/photoviewer?id=96284#1044061','content':'http://t1.search.daumcdn.net/thumb/R438x0.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2Faa1c83c50fe970813bfdb4f792d342e40ca326d5'},'grade2':[{'link':'','content':''}],'photo3':{'link':'http://movie.daum.net/moviedb/photoviewer?id=96284#1044062','content':'http://t1.search.daumcdn.net/thumb/R438x0.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2F237ad289cbb814b332dcbde3dd77776514cd8f27'},'grade1':[{'link':'','content':''}],'photo4':{'link':'http://movie.daum.net/moviedb/photoviewer?id=96284#1044063','content':'http://t1.search.daumcdn.net/thumb/R438x0.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2Ffff8e4c7157b8c35dc5ac3d80d821336c68adb8a'},'ogr_title':[{'link':'','content':''}],'photo5':{'link':'http://movie.daum.net/moviedb/photoviewer?id=96284#1044064','content':'http://t1.search.daumcdn.net/thumb/R438x0.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2F3b50b7fcafb58de99974e91efde28f5cabb2eff1'},'trailer':[{'link':'','content':''}],'audience_date':[{'link':'','content':''}],'write_grades':[{'link':'http://movie.daum.net/moviedetailNetizenPoint.do?movieId=96284','content':''}],'genre':[{'link':'','content':''}],'event':[{'link':'','content':''}],'res':[{'link':'','content':''}],'photo_info':[{'link':'http://movie.daum.net/moviedb/photoviewer?id=96284#start/PhotoList','content':'7'}],'audience':[{'link':'','content':''}],'thumbnail':[{'link':'','content':'http://t1.search.daumcdn.net/thumb/R438x0.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2Fdf3579226616ae7e92b6e3295a0b7e2e350d755c'}],'eng_title':[{'link':'','content':'Kiev/Moscow. Part 1'}],'more_actor':[{'link':'http://movie.daum.net/moviedb/crew?movieId=96284','content':''}],'open_info':[{'link':'','content':''},{'link':'','content':''},{'link':'','content':'70분'},{'link':'','content':''}],'director':[{'link':'http://movie.daum.net/person/main?personId=319599','content':'엘레나 코레바'}],'kword':[{'link':'','content':'키예프/모스크바.'},{'link':'','content':'파트'},{'link':'','content':'1'}],'actor':[{'link':'','content':''}],'photo1':{'link':'http://movie.daum.net/moviedb/photoviewer?id=96284#1044060','content':'http://t1.search.daumcdn.net/thumb/R438x0.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2Fdf3579226616ae7e92b6e3295a0b7e2e350d755c'},'category':[{'link':'','content':'일반'}],'video_info':[{'link':'','content':''}],'story':[{'link':'','content':''}],'homepage':[{'link':'','content':''}]}";
		String requestgetparameter = "{'channel':{'result':10,'title':'Search SCC Open API','page':1,'totalCount':0,'q':'555','item':[],'debug':{},'pk':''}}";

		Object jsonobject = JSONValue.parse(requestgetparameter);
		JSONObject jsonobj = (JSONObject) jsonobject;
		
		System.out.println(jsonobj);

	}

}

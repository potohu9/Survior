package scene;

public class ResultManager {
	public enum ResultList{
		WIN,
		LOSE,
	};
	
	private static ResultList resultList;
	
	public static void setResultList(ResultList resultList) {
		ResultManager.resultList = resultList;
	}
	
	public static ResultList getResultList()	{return resultList;}
	
	static {
		resultList = ResultList.LOSE;
	}
}

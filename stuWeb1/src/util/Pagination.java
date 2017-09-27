package util;

public class Pagination {

	private int yeNum;// 一页显示多少条;
	private int yeMa;// 显示几个页码，如1——5,1——10
	// 换算开始页和结束页
	private int begin;
	private int end;
	// 当前页
	private int ye;
	// 最大页
	private int maxYe;

	public Pagination(int ye, int max, int yeNum, int yeMa) {

		this.ye = ye;
		this.yeNum = yeNum;
		this.yeMa = yeMa;
		cal(max);
	}

	public void cal(int max) {
		// 保证最小的页是第一页
		if (ye < 1) {
			ye = 1;
		}

		// 根据最大记录数，换算出页数
		this.maxYe = max % yeNum == 0 ? max / yeNum : max / yeNum + 1;

		// 保证最大的页是最后一页
		if (ye > maxYe) {
			ye = maxYe;
		}

		// 让当前选中页，显示在中间
		begin = ye - yeMa / 2;
		end = ye + yeMa / 2;
		// 控制页码是1,2的情況
		if (begin < 1) {
			begin = 1;
			end = yeMa;
		}
		// 控制页码是倒数1,2的情況
		if (end > maxYe) {
			end = maxYe;
			begin = end - yeMa + 1;
		}

		// 控制最大页，不足5的情况
		if (maxYe < yeMa) {
			begin = 1;
			end = maxYe;
		}
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getYe() {
		return ye;
	}

	public void setYe(int ye) {
		this.ye = ye;
	}

	public int getMaxYe() {
		return maxYe;
	}

	public void setMaxYe(int maxYe) {
		this.maxYe = maxYe;
	}

}

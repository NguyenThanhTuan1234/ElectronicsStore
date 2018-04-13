package entities;

public class SubCategoryRule {
	private int id;
	private int subCategoryId1;
	private int subCategoryId2;
	private int supCount;
	private double conf;
	public SubCategoryRule() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSubCategoryId1() {
		return subCategoryId1;
	}
	public void setSubCategoryId1(int subCategoryId1) {
		this.subCategoryId1 = subCategoryId1;
	}
	public int getSubCategoryId2() {
		return subCategoryId2;
	}
	public void setSubCategoryId2(int subCategoryId2) {
		this.subCategoryId2 = subCategoryId2;
	}
	public int getSupCount() {
		return supCount;
	}
	public void setSupCount(int supCount) {
		this.supCount = supCount;
	}
	public double getConf() {
		return conf;
	}
	public void setConf(double conf) {
		this.conf = conf;
	}
	
}

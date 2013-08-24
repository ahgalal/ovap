package ovap.filter;

public abstract class VideoFilter {
	protected Link			linkIn, linkOut;
	protected String		name;
	public abstract void enable(boolean enable);
	public abstract FilterData getFilterData();
	public abstract String getID();
	public abstract int getInPortCount();
	public Link getLinkIn(){
		return linkIn;
	}
	public Link getLinkOut(){
		return linkOut;
	}
	public String getName(){
		return name;
	}
	public abstract int getOutPortCount();
	public abstract void process();
	public abstract void registerDependentData(FilterData data);
	public void setLinkIn(Link linkIn){
		this.linkIn=linkIn;
	}
	public void setLinkOut(Link linkOut){
		this.linkOut=linkOut;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public abstract VideoFilter newInstance();
}

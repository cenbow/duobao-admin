package com.aibinong.backyard.pojo;

public class Code {
	 /**
     * resource_id : 14
     * resource_name : 渠道字典
     * parent_id : 12
     * level : 1
     * name : 渠道字典
     * pid : 12
     * isParent : false
     * open : false
     */
	private Long id;
    private int level;
    private String name;
    private Long pId;
    private boolean isParent;
    private boolean open;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getPid() {
		return pId;
	}

	public void setPid(Long pId) {
		this.pId = pId;
	}

	public boolean isIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

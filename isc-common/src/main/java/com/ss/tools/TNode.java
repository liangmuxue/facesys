package com.ss.tools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;


public class TNode<T extends ITNode> extends Object implements ITNode {

    private static final long serialVersionUID = -8865682845335267357L;
    private T data;
    private String id;
    private String code;
    private String title;
    private String parentId;
    private Integer level = Integer.valueOf(1);


    private boolean isLeaf = true;


    private List<TNode<T>> children = new ArrayList();


    public TNode() {
    }


    public TNode(T t) {
        setData(t);

        setId(t.getId());
        setCode(t.getCode());
        setTitle(t.getTitle());

        if (StringUtils.hasText(getParentId())) {
            setLevel(Integer.valueOf(0));
        } else {
            setParentId(t.getParentId());
        }
    }


    public T getData() {
        return (T) this.data;
    }


    public void setData(T item) {
        this.data = item;
    }


    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getCode() {
        return this.code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public Integer getLevel() {
        return this.level;
    }


    public void setLevel(Integer level) {
        this.level = level;
    }


    public boolean isLeaf() {
        return this.isLeaf;
    }


    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public List<TNode<T>> getChildren() {
        return this.children;
    }


    public void setChildren(List<TNode<T>> children) {
        this.children = children;
    }

}

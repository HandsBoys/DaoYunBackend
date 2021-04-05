//package com.dy.core.domain;
//
//import com.dy.domain.SysMenu;
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MenuTree {
//    private static final long serialVersionUID = 1L;
//
//    /** 节点ID */
//    private Long id;
//
//    /** 节点名称 */
//    private String label;
//
//    /** 子节点 */
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private List<MenuTree> children;
//
//    public MenuTree()
//    {
//
//    }
//
//    public MenuTree(SysMenu menu)
//    {
//        this.id = menu.getMenuId();
//        this.label = menu.getMenuName();
//        this.children = menu.getChildren().stream().map(MenuTree::new).collect(Collectors.toList());
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//
//    public void updateId(Long id)
//    {
//        this.id = id;
//    }
//
//    public String getLabel()
//    {
//        return label;
//    }
//
//    public void updateLabel(String label)
//    {
//        this.label = label;
//    }
//
//    public List<MenuTree> getChildren()
//    {
//        return children;
//    }
//
//    public void updateChildren(List<MenuTree> children)
//    {
//        this.children = children;
//    }
//
//    public void buildMenuTree(List<SysMenu> menus){}
//}

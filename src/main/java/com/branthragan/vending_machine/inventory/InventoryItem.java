package com.branthragan.vending_machine.inventory;


public class InventoryItem {
    private Long id;
    private String name;
    private int count;

    public InventoryItem() {
    }

    public InventoryItem(Long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

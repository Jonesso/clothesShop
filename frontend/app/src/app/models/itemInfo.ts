import {ItemInOrder} from './ItemInOrder';

export class ItemInfo {
    itemId: string;
    itemName: string;
    itemPrice: number;
    itemStock: number;
    itemDescription: string;
    itemIcon: string;
    itemStatus: number; // 0: onsale 1: offsale
    categoryType: number;
    createTime: string;
    updateTime: string;


    constructor(itemInOrder?: ItemInOrder) {
        if (itemInOrder) {
            this.itemId = itemInOrder.itemId;
            this.itemName = itemInOrder.itemName;
            this.itemPrice = itemInOrder.itemPrice;
            this.itemStock = itemInOrder.itemStock;
            this.itemDescription = itemInOrder.itemDescription;
            this.itemIcon = itemInOrder.itemIcon;
            this.categoryType = itemInOrder.categoryType;
            this.itemStatus = 0;
        } else {
            this.itemId = '';
            this.itemName = '';
            this.itemPrice = 20;
            this.itemStock = 100;
            this.itemDescription = '';
            this.itemIcon = '';
            this.categoryType = 0;
            this.itemStatus = 0;
        }
    }
}


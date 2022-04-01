import {ItemInfo} from './itemInfo';

export class ItemInOrder {
    itemId: string;
    itemName: string;
    itemPrice: number;
    itemStock: number;
    itemDescription: string;
    itemIcon: string;
    categoryType: number;
    count: number;

    constructor(itemInfo: ItemInfo, quantity = 1){
        this.itemId = itemInfo.itemId;
        this.itemName = itemInfo.itemName;
        this.itemPrice = itemInfo.itemPrice;
        this.itemStock = itemInfo.itemStock;
        this.itemDescription = itemInfo.itemDescription;
        this.itemIcon = itemInfo.itemIcon;
        this.categoryType = itemInfo.categoryType;
        this.count = quantity;
    }
}

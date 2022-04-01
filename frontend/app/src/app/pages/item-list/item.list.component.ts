import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {ItemService} from '../../services/item.service';
import {JwtResponse} from '../../response/JwtResponse';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {CategoryType} from '../../enum/CategoryType';
import {ItemStatus} from '../../enum/ItemStatus';
import {ItemInfo} from '../../models/itemInfo';
import {Role} from '../../enum/Role';
import {faPlus} from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-item.list',
    templateUrl: './item.list.component.html',
    styleUrls: ['./item.list.component.css']
})
export class ItemListComponent implements OnInit, OnDestroy {

    constructor(private userService: UserService,
                private itemService: ItemService,
                private route: ActivatedRoute) {
    }

    Role = Role;
    currentUser: JwtResponse;
    page: any;
    CategoryType = CategoryType;
    ItemStatus = ItemStatus;
    private querySub: Subscription;
    faPlus = faPlus;

    ngOnInit() {
        this.querySub = this.route.queryParams.subscribe(() => {
            this.update();
        });
    }

    ngOnDestroy(): void {
        this.querySub.unsubscribe();
    }

    update() {
        if (this.route.snapshot.queryParamMap.get('page')) {
            const currentPage = +this.route.snapshot.queryParamMap.get('page');
            const size = +this.route.snapshot.queryParamMap.get('size');
            this.getItems(currentPage, size);
        } else {
            this.getItems();
        }
    }

    getItems(page: number = 1, size: number = 5) {
        this.itemService.getAllInPage(+page, +size)
            .subscribe(page => {
                this.page = page;
            });

    }


    remove(itemInfos: ItemInfo[], itemInfo) {
        this.itemService.delelte(itemInfo).subscribe(_ => {
                itemInfos = itemInfos.filter(e => e.itemId !== itemInfo);
            },
            err => {
            });
    }


}

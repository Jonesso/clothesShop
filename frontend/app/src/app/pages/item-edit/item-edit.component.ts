import {AfterContentChecked, Component, OnInit} from '@angular/core';
import {ItemInfo} from '../../models/itemInfo';
import {ItemService} from '../../services/item.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-item-edit',
    templateUrl: './item-edit.component.html',
    styleUrls: ['./item-edit.component.css']
})
export class ItemEditComponent implements OnInit, AfterContentChecked {

    item = new ItemInfo();

    constructor(private itemService: ItemService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    itemId: string;
    isEdit = false;

    ngOnInit() {
        this.itemId = this.route.snapshot.paramMap.get('id');
        if (this.itemId) {
            this.isEdit = true;
            this.itemService.getDetail(this.itemId).subscribe(prod => this.item = prod);
        }

    }

    update() {
        this.itemService.update(this.item).subscribe(prod => {
                if (!prod) { throw new Error(); }
                this.router.navigate(['/seller']);
            },
            err => {
            });

    }

    onSubmit() {
        if (this.itemId) {
            this.update();
        } else {
            this.add();
        }
    }

    add() {
        this.itemService.create(this.item).subscribe(item => {
                // TODO : delete new
                if (!item) { throw new Error; }
                this.router.navigate(['/']);
            },
            e => {
            });
    }

    ngAfterContentChecked(): void {
        console.log(this.item);
    }
}

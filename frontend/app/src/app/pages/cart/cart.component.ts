import {AfterContentChecked, Component, OnDestroy, OnInit} from '@angular/core';
import {CartService} from '../../services/cart.service';
import {Subject, Subscription} from 'rxjs';
import {UserService} from '../../services/user.service';
import {JwtResponse} from '../../response/JwtResponse';
import {ItemInOrder} from '../../models/ItemInOrder';
import {debounceTime, switchMap} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import {Role} from '../../enum/Role';
import {faMinus} from '@fortawesome/free-solid-svg-icons';
import {faPlus} from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, OnDestroy, AfterContentChecked {

    constructor(private cartService: CartService,
                private userService: UserService,
                private router: Router) {
        this.userSubscription = this.userService.currentUser.subscribe(user => this.currentUser = user);
    }

    itemInOrders = [];
    total = 0;
    currentUser: JwtResponse;
    userSubscription: Subscription;

    private updateTerms = new Subject<ItemInOrder>();
    sub: Subscription;

    faMinus = faMinus;
    faPlus = faPlus;

    static validateCount(itemInOrder) {
        const max = itemInOrder.itemStock;
        if (itemInOrder.count > max) {
            itemInOrder.count = max;
        } else if (itemInOrder.count < 1) {
            itemInOrder.count = 1;
        }
        console.log(itemInOrder.count);
    }

    ngOnInit() {
        this.cartService.getCart().subscribe(items => {
            this.itemInOrders = items;
        });

        this.sub = this.updateTerms.pipe(
            // wait 300ms after each keystroke before considering the term
            debounceTime(300),
            //
            // ignore new term if same as previous term
            // Same Object Reference, not working here
            //  distinctUntilChanged((p: ItemInOrder, q: ItemInOrder) => p.count === q.count),
            //
            // switch to new search observable each time the term changes
            switchMap((itemInOrder: ItemInOrder) => this.cartService.update(itemInOrder))
        ).subscribe(item => {
                if (item) { throw new Error(); }
            },
            _ => console.log('Update Item Failed'));
    }

    ngOnDestroy() {
        if (!this.currentUser) {
            this.cartService.storeLocalCart();
        }
        this.userSubscription.unsubscribe();
    }

    ngAfterContentChecked() {
        this.total = this.itemInOrders.reduce(
            (prev, cur) => prev + cur.count * cur.itemPrice, 0);
    }

    addOne(itemInOrder) {
        itemInOrder.count++;
        CartComponent.validateCount(itemInOrder);
        if (this.currentUser) { this.updateTerms.next(itemInOrder); }
    }

    minusOne(itemInOrder) {
        itemInOrder.count--;
        CartComponent.validateCount(itemInOrder);
        if (this.currentUser) { this.updateTerms.next(itemInOrder); }
    }

    onChange(itemInOrder) {
        CartComponent.validateCount(itemInOrder);
        if (this.currentUser) { this.updateTerms.next(itemInOrder); }
    }


    remove(itemInOrder: ItemInOrder) {
        this.cartService.remove(itemInOrder).subscribe(
            success => {
               this.itemInOrders = this.itemInOrders.filter(e => e.itemId !== itemInOrder.itemId);
               console.log('Cart: ' + this.itemInOrders);
            },
            _ => console.log('Remove Cart Failed'));
    }

    checkout() {
        if (!this.currentUser) {
            this.router.navigate(['/login'], {queryParams: {returnUrl: this.router.url}});
        } else if (this.currentUser.role !== Role.Customer) {
            this.router.navigate(['/seller']);
        } else {
            this.cartService.checkout().subscribe(
                _ => {
                    this.itemInOrders = [];
                },
                error1 => {
                    console.log('Checkout Cart Failed');
                });
            this.router.navigate(['/']);
        }

    }
}


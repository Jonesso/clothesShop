import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {ItemInfo} from '../models/itemInfo';
import {apiUrl} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ItemService {

    private itemUrl = `${apiUrl}/item`;
    private categoryUrl = `${apiUrl}/category`;

    constructor(private http: HttpClient) {
    }

    getAllInPage(page: number, size: number): Observable<any> {
        const url = `${this.itemUrl}?page=${page}&size=${size}`;
        return this.http.get(url)
            .pipe(
                // tap(_ => console.log(_)),
            );
    }

    getCategoryInPage(categoryType: number, page: number, size: number): Observable<any> {
        const url = `${this.categoryUrl}/${categoryType}?page=${page}&size=${size}`;
        return this.http.get(url).pipe(
            // tap(data => console.log(data))
        );
    }
    // TODO: String or string
    getDetail(id: string): Observable<ItemInfo> {
        const url = `${this.itemUrl}/${id}`;
        return this.http.get<ItemInfo>(url).pipe(
            catchError(_ => {
                console.log('Get Detail Failed');
                return of(new ItemInfo());
            })
        );
    }

    update(itemInfo: ItemInfo): Observable<ItemInfo> {
        const url = `${apiUrl}/seller/item/${itemInfo.itemId}/edit`;
        return this.http.put<ItemInfo>(url, itemInfo);
    }

    create(productInfo: ItemInfo): Observable<ItemInfo> {
        const url = `${apiUrl}/seller/item/new`;
        return this.http.post<ItemInfo>(url, productInfo);
    }


    delelte(itemInfo: ItemInfo): Observable<any> {
        const url = `${apiUrl}/seller/item/${itemInfo.itemId}/delete`;
        return this.http.delete(url);
    }


    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            console.error(error); // log to console instead

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }
}

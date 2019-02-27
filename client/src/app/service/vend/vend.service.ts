import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class VendService {

  constructor(private http: HttpClient) {
  }

  public getItemList(): Observable<any> {
    return this.http.get('//localhost:8080/vend');
  }

  public insertFunds(): Observable<any> {
    return this.http.post('//localhost:8080/vend/insert-funds', null, httpOptions);
  }

  public ejectFunds(): Observable<any> {
    return this.http.post('//localhost:8080/vend/eject-funds', null, httpOptions);
  }

  public selectItem(itemId: number): Observable<any> {
    return this.http.post(`//localhost:8080/vend/${itemId}`, null, httpOptions);
  }
}

import {Component, OnInit} from '@angular/core';
import {VendService} from '../service/vend.service';

@Component({
  selector: 'app-coin-slot',
  templateUrl: './coin-slot.component.html',
  styleUrls: ['./coin-slot.component.css']
})
export class CoinSlotComponent implements OnInit {

  constructor(private vendService: VendService) {
  }

  ngOnInit() {
  }

  insertCoin(): void {
    this.vendService.insertFunds().subscribe(
      response => console.log(response.message),
      err => console.log(err)
    );
  }

  ejectCoin(): void {
    this.vendService.ejectFunds().subscribe(
      response => console.log(response.message),
      err => console.log(err)
    );
  }

}

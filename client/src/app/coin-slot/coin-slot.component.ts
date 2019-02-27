import {Component, OnInit} from '@angular/core';
import {VendService} from '../service/vend/vend.service';
import {MessageService} from '../service/message/message.service';

@Component({
  selector: 'app-coin-slot',
  templateUrl: './coin-slot.component.html',
  styleUrls: ['./coin-slot.component.css']
})
export class CoinSlotComponent implements OnInit {

  constructor(private vendService: VendService,
              private messageService: MessageService) {
  }

  ngOnInit() {
  }

  insertCoin(): void {
    this.vendService.insertFunds().subscribe(
      response => this.log(response.message),
      err => console.log(err)
    );
  }


  ejectCoin(): void {
    this.vendService.ejectFunds().subscribe(
      response => this.log(response.message),
      err => console.log(err)
    );
  }

  private log(message: string) {
    this.messageService.addMessage(message);
  }
}

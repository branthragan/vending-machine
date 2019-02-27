import {Component, OnInit} from '@angular/core';
import {VendService} from '../service/vend/vend.service';
import {MessageService} from '../service/message/message.service';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  items: Array<any>;

  constructor(private vendService: VendService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.vendService.getItemList().subscribe(items => {
      this.items = items;
    });
  }

  selectItem(id: number): void {
    this.vendService.selectItem(id).subscribe(
      response => this.log(response.message),
      err => console.log(err)
    );
  }

  private log(message: string) {
    this.messageService.addMessage(message);
  }

}

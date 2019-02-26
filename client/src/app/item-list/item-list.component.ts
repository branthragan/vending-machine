import {Component, OnInit} from '@angular/core';
import {VendService} from '../service/vend.service';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  items: Array<any>;

  constructor(private vendService: VendService) {
  }

  ngOnInit() {
    this.vendService.getItemList().subscribe(items => {
      this.items = items;
    });
  }

  selectItem(id: number): void {
    this.vendService.selectItem(id).subscribe(
      response => console.log(response.message),
      err => console.log(err)
    );
  }

}

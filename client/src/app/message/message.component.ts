import {Component, OnInit} from '@angular/core';
import {MessageService} from '../service/message/message.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  constructor(public messageService: MessageService) {
  }

  ngOnInit() {
  }

}

import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private messages: string[] = [];

  constructor() {
  }

  getMostRecentMessage(): string[] {
    const length = this.messages.length;
    return this.messages.slice(length - 1, length);
  }

  public addMessage(message: string): void {
    this.messages.push(message);
  }

}

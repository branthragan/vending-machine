import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CoinSlotComponent} from './coin-slot.component';

describe('CoinSlotComponent', () => {
  let component: CoinSlotComponent;
  let fixture: ComponentFixture<CoinSlotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CoinSlotComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoinSlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

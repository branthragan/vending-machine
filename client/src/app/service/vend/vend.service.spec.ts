import {TestBed} from '@angular/core/testing';

import {VendService} from './vend.service';

describe('VendService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VendService = TestBed.get(VendService);
    expect(service).toBeTruthy();
  });
});

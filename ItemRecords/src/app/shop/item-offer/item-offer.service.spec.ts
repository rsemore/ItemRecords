import { TestBed } from '@angular/core/testing';

import { ItemOfferService } from './item-offer.service';

describe('ItemOfferService', () => {
  let service: ItemOfferService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemOfferService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

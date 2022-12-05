import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemOfferDialogComponent } from './item-offer-dialog.component';

describe('ItemOfferDialogComponent', () => {
  let component: ItemOfferDialogComponent;
  let fixture: ComponentFixture<ItemOfferDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemOfferDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemOfferDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

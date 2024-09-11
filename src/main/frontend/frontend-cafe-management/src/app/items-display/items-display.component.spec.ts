import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsDisplayComponent } from './items-display.component';

describe('ItemsDisplayComponent', () => {
  let component: ItemsDisplayComponent;
  let fixture: ComponentFixture<ItemsDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemsDisplayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ItemsDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

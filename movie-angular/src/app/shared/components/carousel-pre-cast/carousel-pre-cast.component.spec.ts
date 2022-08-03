import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarouselPreCastComponent } from './carousel-pre-cast.component';

describe('CarouselPreCastComponent', () => {
  let component: CarouselPreCastComponent;
  let fixture: ComponentFixture<CarouselPreCastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarouselPreCastComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarouselPreCastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

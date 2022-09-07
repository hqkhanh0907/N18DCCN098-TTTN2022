import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CastPageComponent } from './cast-page.component';

describe('CastPageComponent', () => {
  let component: CastPageComponent;
  let fixture: ComponentFixture<CastPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CastPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CastPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

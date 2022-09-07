import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCastComponent } from './page-cast.component';

describe('PageCastComponent', () => {
  let component: PageCastComponent;
  let fixture: ComponentFixture<PageCastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageCastComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageCastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

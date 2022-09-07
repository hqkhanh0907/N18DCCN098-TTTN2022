import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorChildComponent } from './director-child.component';

describe('DirectorChildComponent', () => {
  let component: DirectorChildComponent;
  let fixture: ComponentFixture<DirectorChildComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DirectorChildComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectorChildComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

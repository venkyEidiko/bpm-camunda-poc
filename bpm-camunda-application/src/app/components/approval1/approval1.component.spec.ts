import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Approval1Component } from './approval1.component';

describe('Approval1Component', () => {
  let component: Approval1Component;
  let fixture: ComponentFixture<Approval1Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Approval1Component]
    });
    fixture = TestBed.createComponent(Approval1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

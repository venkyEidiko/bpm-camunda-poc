import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Approval2Component } from './approval2.component';

describe('Approval2Component', () => {
  let component: Approval2Component;
  let fixture: ComponentFixture<Approval2Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Approval2Component]
    });
    fixture = TestBed.createComponent(Approval2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

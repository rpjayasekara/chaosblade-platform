import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddhostComponent } from './addhost.component';

describe('AddhostComponent', () => {
  let component: AddhostComponent;
  let fixture: ComponentFixture<AddhostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddhostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddhostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

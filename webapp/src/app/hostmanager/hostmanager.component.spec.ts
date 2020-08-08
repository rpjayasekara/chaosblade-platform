import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HostmanagerComponent } from './hostmanager.component';

describe('HostmanagerComponent', () => {
  let component: HostmanagerComponent;
  let fixture: ComponentFixture<HostmanagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HostmanagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HostmanagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

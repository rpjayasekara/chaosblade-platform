import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { FormGroup } from '@angular/forms';


@Injectable({
  providedIn: 'root'
})
export class ExperimentService {

  constructor(private http: HttpClient) { }

  getExperiments(): Observable<object> {
    return this.http.get('http://127.0.0.1:8080/chaosblade/experiment');
  }

  getHosts(): Observable<object> {
    return this.http.get('http://127.0.0.1:8080/chaosblade/hosts');
  }

  createExperiment(experiment: any): Observable<object> {
    return this.http.post('http://127.0.0.1:8080/chaosblade/experiment', experiment);
  }

  addHost(host: any): Observable<object> {
    return this.http.post('http://127.0.0.1:8080/chaosblade/hosts', host);
  }

  getExperimentRecords(): Observable<object> {
    return this.http.get('http://127.0.0.1:8080/chaosblade/records');
  }

  destroyExperiment(experiment: any): Observable<object> {
    return this.http.post('http://127.0.0.1:8080/chaosblade/experimentdelete', experiment);
  }

}

import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  name = 'Angular';
  public targetList = [];
  public actionList = [];
  public matcherList = [];
  public flagList = [];
  public userForm: FormGroup;
  public createExperimentForm: FormGroup;
  testObjectModel = [{
    target: 'cpu',
    actions: [
      {
        action: 'fullload 1',
        matchers: [
          {
            matcher: 'process',
            noArgs: false,
            required: true
          },
          {
            matcher: 'test matcher',
            noArgs: true,
            required: true
          }
        ],
        flags: [
          {
            matcher: 'test flag1',
            noArgs: true,
            required: false
          },
          {
            matcher: 'test flag2',
            noArgs: false,
            required: false
          }
        ]
      },
      {
        action: 'fullload 2',
        matchers: [
          {
            matcher: 'process',
            noArgs: false,
            required: true
          },
          {
            matcher: 'test matcher',
            noArgs: true,
            required: true
          }
        ],
        flags: [
          {
            matcher: 'test flag3',
            noArgs: false,
            required: false
          },
          {
            matcher: 'test flag4',
            noArgs: false,
            required: false
          }
        ]
      }
    ]
  },
    {
      target: 'test target2',
      actions: [
        {
          action: 'fullload 3',
          matchers: [
            {
              matcher: 'process',
              noArgs: false,
              required: true
            },
            {
              matcher: 'test matcher',
              noArgs: true,
              required: true
            }
          ],
          flags: [
            {
              matcher: 'test flag1',
              noArgs: false,
              required: false
            },
            {
              matcher: 'test flag2',
              noArgs: false,
              required: false
            }
          ]
        },
        {
          action: 'fullload 4',
          matchers: [
            {
              matcher: 'process',
              noArgs: false,
              required: true
            },
            {
              matcher: 'test matcher',
              noArgs: true,
              required: true
            }
          ],
          flags: [
            {
              matcher: 'test flag1',
              noArgs: false,
              required: false
            },
            {
              matcher: 'test flag2',
              noArgs: false,
              required: false
            }
          ]
        }
      ]
    }];

  // tslint:disable-next-line:variable-name
  constructor(private _fb: FormBuilder) {
    this.setActionList()
    this.createExperimentForm = this._fb.group({
      target: [],
      action: [],
      host: [],
      matchers: this._fb.array([]),
      flags: this._fb.array([])
    });
  }

  private addMatcherGroup(): FormGroup {
    return this._fb.group({
      matcherName: [],
      matcherValue: []
    });
  }

  private addFlagGroup(): FormGroup {
    return this._fb.group({
      flagName: [],
      flagValue: []
    });
  }

  addMatcher(): void {
    console.log(this.createExperimentForm.value);
    this.matcherArray.push(this.addMatcherGroup());
  }

  addFlag(): void {
    console.log(this.createExperimentForm.value);
    if (this.flagArray.length < this.flagList.length){
      this.flagArray.push(this.addFlagGroup());
    }
  }


  removeMatcher(index: number): void {
    this.matcherArray.removeAt(index);
  }

  removeFlag(index: number): void {
    this.flagArray.removeAt(index);
  }

  get matcherArray(): FormArray {
    return this.createExperimentForm.get('matchers') as FormArray;
  }

  get flagArray(): FormArray {
    return this.createExperimentForm.get('flags') as FormArray;
  }

  testChange(): void {
    console.log(this.userForm.value);
  }

  changeActionList(): void {
    // tslint:disable-next-line:prefer-for-of
    this.actionList = [];
    const target = this.createExperimentForm.value.target;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.testObjectModel.length; i++){
      const experimentObject = this.testObjectModel[i];
      if (experimentObject.target === target){
        // tslint:disable-next-line:forin prefer-for-of
        for (let j = 0; j < experimentObject.actions.length; j++){
          this.actionList.push(experimentObject.actions[j].action);
        }
      }
    }
  }

  changeFlagList(): void {
    this.flagList = []
    const action = this.createExperimentForm.value.action;
    const target = this.createExperimentForm.value.target;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.testObjectModel.length; i++){
      const experimentObject = this.testObjectModel[i];
      if (experimentObject.target === target){
        // tslint:disable-next-line:forin prefer-for-of
        for (let j = 0; j < experimentObject.actions.length; j++){
          if (experimentObject.actions[j].action === action){
            this.flagList = experimentObject.actions[j].flags;
          }
        }
      }
    }

  }

  ngOnInit(): void {
  }

  private setActionList(): void {
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.testObjectModel.length; i++){
      const experimentObject = this.testObjectModel[i];
      this.targetList.push(experimentObject.target);
    }
  }

  public updateDisabilityFlag(index: number, disable: boolean): void {
    // tslint:disable-next-line:forin
    for (const i in this.flagList){
      if (this.flagList[i].matcher === this.flagArray.at(index).value.flagName){
        if (this.flagList[i].noArgs) {
          this.flagArray.at(index)['controls']['flagValue'].disable();
        } else {
          this.flagArray.at(index)['controls']['flagValue'].enable();
        }
      }
    }

  }

}

/* eslint-disable */
export type Maybe<T> = T | null;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  OffsetDateTime: any;
};

export type GqlCompany = {
  __typename?: 'Company';
  id: Scalars['ID'];
  name: Scalars['String'];
};

export type GqlCompanyInput = {
  name: Scalars['String'];
};

export type GqlCompanyUpdate = {
  __typename?: 'CompanyUpdate';
  type: GqlEntityUpdateType;
  entity: GqlCompany;
};

export enum GqlEntityUpdateType {
  Deleted = 'DELETED',
  Updated = 'UPDATED',
  Created = 'CREATED'
}

export type GqlFileMeta = {
  __typename?: 'FileMeta';
  id: Scalars['String'];
  url: Scalars['String'];
  mimeType: Scalars['String'];
};

export type GqlFileMetaInput = {
  id: Scalars['String'];
  url: Scalars['String'];
  mimeType: Scalars['String'];
};

export type GqlMutation = {
  __typename?: 'Mutation';
  /** company */
  createCompany: GqlCompany;
  updateCompany: GqlCompany;
  deleteCompany: GqlCompany;
  /** project */
  createProject: GqlProject;
  updateProject: GqlProject;
  deleteProject: GqlProject;
  /** user */
  createUser: GqlUser;
  updateUser: GqlUser;
  deleteUser: GqlUser;
  /** time log */
  createTimeLog: GqlTimeLog;
  updateTimeLog: GqlTimeLog;
  deleteTimeLog: GqlTimeLog;
};


export type GqlMutationCreateCompanyArgs = {
  input: GqlCompanyInput;
};


export type GqlMutationUpdateCompanyArgs = {
  id: Scalars['ID'];
  input: GqlCompanyInput;
};


export type GqlMutationDeleteCompanyArgs = {
  id: Scalars['ID'];
};


export type GqlMutationCreateProjectArgs = {
  input: GqlProjectInput;
};


export type GqlMutationUpdateProjectArgs = {
  id: Scalars['ID'];
  input: GqlProjectInput;
};


export type GqlMutationDeleteProjectArgs = {
  id: Scalars['ID'];
};


export type GqlMutationCreateUserArgs = {
  input: GqlUserInput;
};


export type GqlMutationUpdateUserArgs = {
  id: Scalars['ID'];
  input: GqlUserInput;
};


export type GqlMutationDeleteUserArgs = {
  id: Scalars['ID'];
};


export type GqlMutationCreateTimeLogArgs = {
  input: GqlTimeLogInput;
};


export type GqlMutationUpdateTimeLogArgs = {
  id: Scalars['ID'];
  input: GqlTimeLogInput;
};


export type GqlMutationDeleteTimeLogArgs = {
  id: Scalars['ID'];
};


export type GqlProject = {
  __typename?: 'Project';
  id: Scalars['ID'];
  name: Scalars['String'];
  state: GqlProjectState;
  company?: Maybe<GqlCompany>;
};

export type GqlProjectInput = {
  name: Scalars['String'];
  company: Scalars['ID'];
  state?: Maybe<GqlProjectState>;
};

export enum GqlProjectState {
  Planned = 'PLANNED',
  InProgress = 'IN_PROGRESS',
  Completed = 'COMPLETED',
  Canceled = 'CANCELED',
  PostProcessing = 'POST_PROCESSING'
}

export type GqlProjectUpdate = {
  __typename?: 'ProjectUpdate';
  type: GqlEntityUpdateType;
  entity: GqlProject;
};

export type GqlQuery = {
  __typename?: 'Query';
  /** company */
  company: GqlCompany;
  companies: Array<GqlCompany>;
  /** project */
  project: GqlProject;
  projects: Array<GqlProject>;
  /** user */
  user: GqlUser;
  users: Array<GqlUser>;
  currentUser: GqlUser;
  /** time log */
  timeLogs: Array<GqlTimeLog>;
};


export type GqlQueryCompanyArgs = {
  id: Scalars['ID'];
};


export type GqlQueryProjectArgs = {
  id: Scalars['ID'];
};


export type GqlQueryProjectsArgs = {
  companies?: Maybe<Array<Scalars['ID']>>;
};


export type GqlQueryUserArgs = {
  id: Scalars['ID'];
};


export type GqlQueryTimeLogsArgs = {
  from?: Maybe<Scalars['OffsetDateTime']>;
  to?: Maybe<Scalars['OffsetDateTime']>;
  userId?: Maybe<Scalars['String']>;
  projectId?: Maybe<Scalars['String']>;
};

export type GqlSubscription = {
  __typename?: 'Subscription';
  companyUpdate: GqlCompanyUpdate;
  projectUpdate: GqlProjectUpdate;
  userUpdate: GqlUserUpdate;
  timeLogUpdate: GqlTimeLogUpdate;
};

export type GqlTimeLog = {
  __typename?: 'TimeLog';
  id: Scalars['ID'];
  user?: Maybe<GqlUser>;
  project?: Maybe<GqlProject>;
  description?: Maybe<Scalars['String']>;
  start: Scalars['OffsetDateTime'];
  durationInMinutes: Scalars['Int'];
  breakInMinutes?: Maybe<Scalars['Int']>;
};

export type GqlTimeLogInput = {
  user: Scalars['String'];
  project: Scalars['String'];
  start: Scalars['OffsetDateTime'];
  description?: Maybe<Scalars['String']>;
  durationInMinutes: Scalars['Int'];
  breakInMinutes?: Maybe<Scalars['Int']>;
};

export type GqlTimeLogUpdate = {
  __typename?: 'TimeLogUpdate';
  type: GqlEntityUpdateType;
  entity: GqlTimeLog;
};

export type GqlUser = {
  __typename?: 'User';
  id: Scalars['ID'];
  firstName: Scalars['String'];
  lastName: Scalars['String'];
  email: Scalars['String'];
  admin?: Maybe<Scalars['Boolean']>;
  signature?: Maybe<GqlFileMeta>;
  avatar?: Maybe<GqlFileMeta>;
};

export type GqlUserInput = {
  firstName: Scalars['String'];
  lastName?: Maybe<Scalars['String']>;
  email: Scalars['String'];
  admin?: Maybe<Scalars['Boolean']>;
  signature?: Maybe<GqlFileMetaInput>;
  avatar?: Maybe<GqlFileMetaInput>;
};

export type GqlUserUpdate = {
  __typename?: 'UserUpdate';
  type: GqlEntityUpdateType;
  entity: GqlUser;
};

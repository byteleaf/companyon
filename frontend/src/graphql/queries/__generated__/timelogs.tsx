/* eslint-disable */
import * as Types from '../../types';

import { GqlFullTimeLogFragment } from '../../fragments/__generated__/fullTimeLog';
import { gql } from '@apollo/client';
import { FullTimeLogFragmentDoc } from '../../fragments/__generated__/fullTimeLog';
import * as Apollo from '@apollo/client';
export type GqlTimeLogsQueryVariables = Types.Exact<{
  userId?: Types.Maybe<Types.Scalars['String']>;
  projectId?: Types.Maybe<Types.Scalars['String']>;
  from?: Types.Maybe<Types.Scalars['OffsetDateTime']>;
  to?: Types.Maybe<Types.Scalars['OffsetDateTime']>;
}>;


export type GqlTimeLogsQuery = (
  { __typename?: 'Query' }
  & { timeLogs: Array<(
    { __typename?: 'TimeLog' }
    & GqlFullTimeLogFragment
  )> }
);


export const TimeLogsDocument = gql`
    query timeLogs($userId: String, $projectId: String, $from: OffsetDateTime, $to: OffsetDateTime) {
  timeLogs(userId: $userId, projectId: $projectId, from: $from, to: $to) {
    ...fullTimeLog
  }
}
    ${FullTimeLogFragmentDoc}`;

/**
 * __useTimeLogsQuery__
 *
 * To run a query within a React component, call `useTimeLogsQuery` and pass it any options that fit your needs.
 * When your component renders, `useTimeLogsQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useTimeLogsQuery({
 *   variables: {
 *      userId: // value for 'userId'
 *      projectId: // value for 'projectId'
 *      from: // value for 'from'
 *      to: // value for 'to'
 *   },
 * });
 */
export function useTimeLogsQuery(baseOptions?: Apollo.QueryHookOptions<GqlTimeLogsQuery, GqlTimeLogsQueryVariables>) {
        return Apollo.useQuery<GqlTimeLogsQuery, GqlTimeLogsQueryVariables>(TimeLogsDocument, baseOptions);
      }
export function useTimeLogsLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GqlTimeLogsQuery, GqlTimeLogsQueryVariables>) {
          return Apollo.useLazyQuery<GqlTimeLogsQuery, GqlTimeLogsQueryVariables>(TimeLogsDocument, baseOptions);
        }
export type TimeLogsQueryHookResult = ReturnType<typeof useTimeLogsQuery>;
export type TimeLogsLazyQueryHookResult = ReturnType<typeof useTimeLogsLazyQuery>;
export type TimeLogsQueryResult = Apollo.QueryResult<GqlTimeLogsQuery, GqlTimeLogsQueryVariables>;